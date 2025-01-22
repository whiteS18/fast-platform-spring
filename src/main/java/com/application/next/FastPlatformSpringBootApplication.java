package com.application.next;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FastPlatformSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(FastPlatformSpringBootApplication.class, args);
    }
}