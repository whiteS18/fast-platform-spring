// package com.application.next.config.datasource;

// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
// import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

// import javax.sql.DataSource;

@Configuration
@EnableJpaRepositories(
       basePackages = "com.application.next.repository.mysql",
       entityManagerFactoryRef = "mysqlEntityManagerFactory",
       transactionManagerRef = "dynamicTransactionManager"
)
public class MysqlJpaConfig extends AbstractDataSourceConfig {

   @Override
   protected String getHibernateDialect() {
       return "org.hibernate.dialect.MySQLDialect";
   }

   @Override
   protected String getEntityPackage() {
       return "com.application.next.bean.mysql";
   }

   @Override
   protected String getPersistenceUnitName() {
       return "mysql";
   }

   @Bean(name = "mysqlEntityManagerFactory")
   public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
           EntityManagerFactoryBuilder builder,
           @Qualifier("dynamicDataSource") DataSource dataSource) {
       return super.entityManagerFactory(builder, dataSource);
   }
}
