package com.application.next.annotation;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperationLogAnnotation {
    /**
     * 操作类型
     */
    String operationType() default "";
    
    /**
     * 资源名称
     */
    String resourceName() default "";
    
    /**
     * 操作内容
     */
    String operationContent() default "";
    
    /**
     * 操作结果
     */
    String operationResult() default "SUCCESS";
    
    /**
     * 是否从 Javadoc 获取操作内容
     */
    boolean useJavadoc() default false;
} 