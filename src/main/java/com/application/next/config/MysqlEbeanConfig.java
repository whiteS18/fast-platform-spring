package com.application.next.config;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder; // 使用 Spring Boot 的 Builder
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class MysqlEbeanConfig {

    @Bean(name = "mysqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.mysql")
    public DataSource mysqlDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "mysqlDatabase")
    public Database mysqlDatabase(@Qualifier("mysqlDataSource") DataSource dataSource) {
        DatabaseConfig config = new DatabaseConfig();
        config.setName("mysql");
        config.setDataSource(dataSource);
        config.setDefaultServer(false); // 确保非默认
        config.setPackages(Arrays.asList("com.application.next.bean.model.mysql"));
        config.setDdlGenerate(false);
        config.setDdlRun(false);
        return DatabaseFactory.create(config);
    }
}