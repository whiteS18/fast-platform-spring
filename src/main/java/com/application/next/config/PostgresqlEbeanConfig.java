package com.application.next.config;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
public class PostgresqlEbeanConfig {

    @Bean(name = "postgresqlDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.postgresql")
    public DataSource postgresqlDataSource() {
        return DataSourceBuilder.create().build(); // 使用Spring的Builder
    }

    @Bean(name = "postgresqlDatabase")
    public Database postgresqlDatabase(@Qualifier("postgresqlDataSource") DataSource dataSource) {
        DatabaseConfig config = new DatabaseConfig();
        config.setName("postgresql");
        config.setDataSource(dataSource);
        config.setDefaultServer(true); // 设为默认数据源
        config.setPackages(Arrays.asList("com.application.next.bean.model.postgresql"));
        config.setDdlGenerate(true);
        config.setDdlRun(true);
        return DatabaseFactory.create(config);
    }
}