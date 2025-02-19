package com.application.next.config.datasource;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableTransactionManagement
public abstract class AbstractDataSourceConfig {

   /**
    * 创建并配置JPA的EntityManagerFactoryBean。
    * 该方法使用提供的DataSource和EntityManagerFactoryBuilder来构建EntityManagerFactoryBean，
    * 并设置Hibernate的相关属性。
    *
    * @param builder 用于构建EntityManagerFactory的构建器
    * @param dataSource 动态数据源，用于EntityManagerFactory的数据源配置
    * @return 配置好的LocalContainerEntityManagerFactoryBean实例
    */
   @Bean
   public LocalContainerEntityManagerFactoryBean entityManagerFactory(
           EntityManagerFactoryBuilder builder,
           @Qualifier("dynamicDataSource") DataSource dataSource) {
       // 配置Hibernate属性
       Map<String, String> properties = new HashMap<>();
       properties.put("hibernate.dialect", getHibernateDialect());

       // 构建EntityManagerFactoryBean并设置相关属性
       LocalContainerEntityManagerFactoryBean emf = builder
               .dataSource(dataSource)
               .packages(getEntityPackage())
               .properties(properties)
               .persistenceUnit(getPersistenceUnitName())
               .build();
       emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
       return emf;
   }

   /**
    * 获取Hibernate方言。
    * 该方法由子类实现，用于返回特定数据库的Hibernate方言。
    *
    * @return Hibernate方言的字符串表示
    */
   protected abstract String getHibernateDialect();

   /**
    * 获取实体类所在的包路径。
    * 该方法由子类实现，用于返回实体类所在的包路径。
    *
    * @return 实体类所在的包路径
    */
   protected abstract String getEntityPackage();

   /**
    * 获取持久化单元的名称。
    * 该方法由子类实现，用于返回持久化单元的名称。
    *
    * @return 持久化单元的名称
    */
   protected abstract String getPersistenceUnitName();
}