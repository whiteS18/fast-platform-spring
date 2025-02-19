package com.application.next.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {

    @Bean
    public DataSource dynamicDataSource(
            @Qualifier("postgresqlDataSource") DataSource postgresqlDataSource,
            @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {

        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put("postgresqlDataSource", postgresqlDataSource);
        targetDataSources.put("mysqlDataSource", mysqlDataSource);

        DynamicDataSourceRouting routing = new DynamicDataSourceRouting();
        routing.setDefaultTargetDataSource(postgresqlDataSource);
        routing.setTargetDataSources(targetDataSources);
        return routing;
    }
}