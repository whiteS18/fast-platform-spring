package com.application.next.config.datasource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.application.next.repository.postgresql",
        entityManagerFactoryRef = "postgresqlEntityManagerFactory",
        transactionManagerRef = "dynamicTransactionManager"
)
public class PostgresqlJpaConfig extends AbstractDataSourceConfig {

    @Override
    protected String getHibernateDialect() {
        return "org.hibernate.dialect.PostgreSQLDialect";
    }

    @Override
    protected String getEntityPackage() {
        return "com.application.next.bean.postgresql";
    }

    @Override
    protected String getPersistenceUnitName() {
        return "postgresql";
    }

    @Bean(name = "postgresqlEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("dynamicDataSource") DataSource dataSource) {
        return super.entityManagerFactory(builder, dataSource);
    }
}