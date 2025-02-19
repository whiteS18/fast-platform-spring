// package com.application.next.config.datasource;

// import org.springframework.boot.context.properties.ConfigurationProperties;
// import org.springframework.boot.jdbc.DataSourceBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// import com.zaxxer.hikari.HikariDataSource;

// import javax.sql.DataSource;

// @Configuration
// public class MysqlDataSourceConfig {

//     @Bean(name = "mysqlDataSource")
//     @ConfigurationProperties(prefix = "datasource.mysql")
//     public DataSource mysqlDataSource() {
//         return DataSourceBuilder.create().type(HikariDataSource.class).build();
//     }
// }