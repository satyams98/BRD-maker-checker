package com.brd.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.sql.Driver;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.brd.dao", "com.brd.service"})
public class HibernateConfig {
    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties properties = new Properties();
        properties.put(DRIVER, environment.getProperty("oracle.driver"));
        properties.put(URL, environment.getProperty("oracle.url"));
        properties.put(USER, environment.getProperty("oracle.user"));
        properties.put(PASS, environment.getProperty("oracle.password"));

        properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));

        properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT,
                environment.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setPackagesToScan("com.brd.entity");

        return factoryBean;

    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
