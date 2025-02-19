package com.application.next.bean.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "datasource.postgresql")
public class PostgresqlProperties {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    
    @Data
    public static class Hikari {
        private int minimumIdle;
        private int maximumPoolSize;
        private int idleTimeout;
        private int connectionTimeout;
        private int maxLifetime;
    }
    
    private Hikari hikari;
}