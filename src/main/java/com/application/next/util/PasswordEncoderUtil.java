package com.application.next.util;

import cn.dev33.satoken.secure.BCrypt;
import org.springframework.stereotype.Component;
import java.security.SecureRandom;

@Component
public class PasswordEncoderUtil {
    
    public String encode(String password) {
        // 使用 Sa-Token 的 BCrypt 加密
        return BCrypt.hashpw(password, BCrypt.gensalt(14,  new SecureRandom()));
    }

    public boolean matches(String rawPassword, String encodedPassword) {
        return BCrypt.checkpw(rawPassword, encodedPassword);
    }
} 