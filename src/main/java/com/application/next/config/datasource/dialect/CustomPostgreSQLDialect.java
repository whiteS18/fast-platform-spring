package com.application.next.config.datasource.dialect;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.dialect.PostgreSQLDialect;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.descriptor.jdbc.spi.JdbcTypeRegistry;

public class CustomPostgreSQLDialect extends PostgreSQLDialect {

    @Override
    public void contributeTypes(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        super.contributeTypes(typeContributions, serviceRegistry);
        // 获取类型注册表
        JdbcTypeRegistry registry = typeContributions.getTypeConfiguration().getJdbcTypeRegistry();

// 注册 CLOB 到 TEXT 的映射
        registry.addDescriptor(
                java.sql.Types.CLOB,
                registry.getDescriptor(java.sql.Types.LONGNVARCHAR) // 直接使用 PostgreSQL 的 TEXT 类型代码
        );
    }
}