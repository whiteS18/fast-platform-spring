package com.application.next.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.application.next.annotation.OperationLogAnnotation;
import com.application.next.bean.dto.LoginRequest;
import com.application.next.bean.dto.LoginResponse;
import com.application.next.bean.entity.postgresql.UserEntity;
import com.application.next.repository.UserEntityRepository;
import com.application.next.service.AuthService;
import com.application.next.util.PasswordEncoderUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserEntityRepository userRepository;
    private final PasswordEncoderUtil passwordEncoder;

    @Override
    @Transactional
    @OperationLogAnnotation(
        operationType = "LOGIN",
        resourceName = "用户登录",
        resourcePath = "/api/v1/auth/login",
        operationContent = "用户登录系统"
    )
    public LoginResponse login(LoginRequest loginRequest) {
        // 查找用户
        UserEntity user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("用户名或密码错误"));

        // 验证密码
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("用户名或密码错误");
        }

        // 使用 Sa-Token 登录
        StpUtil.login(user.getId());

        // 构建响应
        LoginResponse response = new LoginResponse();
        response.setToken(StpUtil.getTokenValue());
        response.setUsername(user.getUsername());
        response.setNickname(user.getNickname());
        response.setAvatar(user.getAvatar());
        return response;
    }
} 