// package com.application.next.config.datasource;

import com.zaxxer.hikari.HikariConfig;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

// import com.zaxxer.hikari.HikariDataSource;

// import javax.sql.DataSource;

// @Configuration
// public class PostgresqlDataSourceConfig {

    @Primary
    @Bean(name = "postgresqlDataSourceProperties")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSourceProperties postgresqlDataSourceProperties() {

        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql.hikari")
    public DataSource postgresqlDataSource() {

        return postgresqlDataSourceProperties().initializeDataSourceBuilder().type(HikariDataSource.class).build();
    }

}
