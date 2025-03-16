package com.application.next.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.application.next.annotation.OperationLogAnnotation;
import com.application.next.bean.entity.postgresql.OperationLog;
import com.application.next.service.OperationLogService;
import com.application.next.util.RequestWrapper;
import com.application.next.utils.JsonUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Aspect
@Component
@RequiredArgsConstructor
public class OperationLogAspect {

    private final OperationLogService operationLogService;

    @Around("@annotation(operationLog)")
    public Object around(ProceedingJoinPoint point, OperationLogAnnotation operationLog) throws Throwable {
        // 获取当前请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            return point.proceed();
        }
        
        HttpServletRequest request = attributes.getRequest();
        
        // 执行原方法
        Object result = point.proceed();
        
        // 获取方法签名
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        
        // 记录操作日志
        OperationLog log = new OperationLog();
        log.setOperationType(operationLog.operationType());
        log.setResourceName(operationLog.resourceName());
        log.setResourcePath(getResourcePath(method));
        log.setOperationContent(operationLog.useJavadoc() ? getJavadocContent(method) : operationLog.operationContent());
        log.setOperationResult(operationLog.operationResult());
        log.setRequestUrl(request.getRequestURL().toString());
        log.setRequestMethod(request.getMethod());
        log.setOperatorUsername(StpUtil.getLoginIdAsString());
        log.setOperatorIp(getClientIp(request));
        log.setOperationTime(LocalDateTime.now());
        
        // 记录请求头和请求参数
        log.setRequestHeaders(getRequestHeaders(request));
        log.setRequestParams(getRequestParams(request, point.getArgs(), method));
        
        operationLogService.save(log);
        
        return result;
    }

    private String getRequestHeaders(HttpServletRequest request) {
        Enumeration<String> headerNames = request.getHeaderNames();
        Map<String, String> headers = new HashMap<>();
        
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        
        try {
            return JsonUtils.objectToJson(headers);
        } catch (Exception e) {
            return "{}";
        }
    }

    private String getRequestParams(HttpServletRequest request, Object[] args, Method method) {
        Map<String, Object> params = new HashMap<>();
        
        // 获取查询参数
        Map<String, String[]> queryParams = request.getParameterMap();
        if (!queryParams.isEmpty()) {
            params.put("query", queryParams);
        }
        
        // 获取路径参数
        Map<String, String> pathParams = getPathParams(request, method);
        if (!pathParams.isEmpty()) {
            params.put("path", pathParams);
        }
        
        // 获取请求体
        String body = getRequestBody(request);
        if (body != null && !body.isEmpty()) {
            params.put("body", body);
        }
        
        try {
            return JsonUtils.objectToJson(params);
        } catch (Exception e) {
            return "{}";
        }
    }

    private Map<String, String> getPathParams(HttpServletRequest request, Method method) {
        Map<String, String> pathParams = new HashMap<>();
        
        // 获取方法上的路径参数注解
        Parameter[] parameters = method.getParameters();
        for (Parameter parameter : parameters) {
            PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
            if (pathVariable != null) {
                String name = pathVariable.value().isEmpty() ? parameter.getName() : pathVariable.value();
                pathParams.put(name, request.getParameter(name));
            }
        }
        
        return pathParams;
    }

    private String getRequestBody(HttpServletRequest request) {
        try {
            // 保存原始请求体
            String body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
            // 重新包装请求，使请求体可重复读取
            request = new RequestWrapper(request);
            return body;
        } catch (IOException e) {
            return null;
        }
    }

    private String getResourcePath(Method method) {
        StringBuilder path = new StringBuilder();
        
        // 获取类上的基础路径
        String classPath = getClassPath(method.getDeclaringClass());
        if (!classPath.isEmpty()) {
            path.append(classPath);
        }
        
        // 获取方法上的路径
        String methodPath = getMethodPath(method);
        if (!methodPath.isEmpty()) {
            if ( !path.isEmpty() && !path.toString().endsWith("/")) {
                path.append("/");
            }
            path.append(methodPath);
        }
        
        return path.toString();
    }

    private String getClassPath(Class<?> clazz) {
        // 检查类上的 RequestMapping 注解
        RequestMapping requestMapping = clazz.getAnnotation(RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            return requestMapping.value()[0];
        }
        
        // 检查其他路径映射注解
        return getPathFromAnnotation(clazz.getAnnotations());
    }

    private String getMethodPath(Method method) {
        // 检查方法上的 RequestMapping 注解
        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            return requestMapping.value()[0];
        }
        
        // 检查其他路径映射注解
        return getPathFromAnnotation(method.getAnnotations());
    }

    private String getPathFromAnnotation(Annotation[] annotations) {
        // 定义所有可能的路径映射注解
        @SuppressWarnings("unchecked")
        Class<? extends Annotation>[] pathAnnotations = new Class[] {
            GetMapping.class, PostMapping.class, PutMapping.class, DeleteMapping.class,
            PatchMapping.class, RequestMapping.class
        };
        
        // 遍历所有注解查找路径
        for (Class<? extends Annotation> annotationClass : pathAnnotations) {
            Optional<Annotation> annotation = Arrays.stream(annotations)
                .filter(a -> a.annotationType().equals(annotationClass))
                .findFirst();
                
            if (annotation.isPresent()) {
                try {
                    // 获取注解的 value 方法
                    Method valueMethod = annotationClass.getMethod("value");
                    String[] values = (String[]) valueMethod.invoke(annotation.get());
                    if (values != null && values.length > 0) {
                        return values[0];
                    }
                } catch (Exception ignored) {
                    // 忽略反射异常
                }
            }
        }
        
        return "";
    }

    private String getJavadocContent(Method method) {
        // 获取方法的 Javadoc 注释
        String javadoc = method.getAnnotation(OperationLogAnnotation.class).operationContent();
        if (javadoc.isEmpty()) {
            // 如果没有设置 operationContent，则使用方法的 Javadoc 注释
            javadoc = method.getAnnotation(Deprecated.class) != null ? 
                method.getAnnotation(Deprecated.class).toString() : 
                method.toString();
        }
        return javadoc;
    }

    private String getClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.isEmpty() || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
} 