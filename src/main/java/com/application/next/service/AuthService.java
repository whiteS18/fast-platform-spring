package com.application.next.service;

import com.application.next.bean.dto.LoginRequest;
import com.application.next.bean.dto.LoginResponse;

public interface AuthService {
    /**
     * 用户登录
     *
     * @param loginRequest 登录请求
     * @return 登录响应
     */
    LoginResponse login(LoginRequest loginRequest);
} 