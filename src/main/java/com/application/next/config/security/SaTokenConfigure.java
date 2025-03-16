package com.application.next.config.security;

import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SaTokenConfigure {


//     // 注册拦截器
//     @Override
//     public void addInterceptors(InterceptorRegistry registry) {
//         // 注册 Sa-Token 拦截器，校验规则为 StpUtil.checkLogin() 登录校验。
//         registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
//                 .addPathPatterns("/**")
//                 .excludePathPatterns("/user/doLogin");
//     }


    // 注册 Sa-Token 全局过滤器
    @Bean
    public SaServletFilter getSaServletFilter() {
        return new SaServletFilter()
                .addInclude("/**")
                .addExclude("/login", "/logout")
                .setAuth(obj -> {
                    // 校验登录状态
                    SaRouter.match("/**", StpUtil::checkLogin);

                    // 权限校验
                    SaRouter.match("/api/**", () -> StpUtil.checkPermission("api"));
                });
    }

}