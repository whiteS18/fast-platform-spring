package com.application.next.controller;

import com.application.next.annotation.OperationLogAnnotation;
import com.application.next.bean.dto.LoginRequest;
import com.application.next.bean.dto.LoginResponse;
import com.application.next.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户认证控制器
 */
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * 用户登录
     * 验证用户名和密码，生成登录令牌
     *
     * @param loginRequest 登录请求参数
     * @return 登录响应，包含用户信息和令牌
     */
    @PostMapping("/login")
    @OperationLogAnnotation(
        operationType = "LOGIN",
        resourceName = "用户登录",
        useJavadoc = true
    )
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authService.login(loginRequest));
    }
} 