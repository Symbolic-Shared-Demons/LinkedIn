package com.ex;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class ORMConfig {
    @Value("${connection.driver_class}")
    private String driverName;

    @Value("${connection.url}")
    private String url;

    @Value("${connection.username}")
    private String username;

    @Value("${connection.password}")
    private String password;

    @Value("${hibernate.db.dialect}")
    private String dbDialect;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driverName);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        System.out.println("datasource");
        return ds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactoryBean(DataSource ds){
        LocalSessionFactoryBean sfBean = new LocalSessionFactoryBean();
        sfBean.setDataSource(ds);
        sfBean.setPackagesToScan("com.ex.models");
        sfBean.setHibernateProperties(getSfProps());
        System.out.println("sessionFact");
        return sfBean;
    }

    @Bean
    HibernateTransactionManager transactionManager(SessionFactory sf) {
        HibernateTransactionManager manager = new HibernateTransactionManager();
        manager.setSessionFactory(sf);
        System.out.println("Transaction");
        return manager;
    }

    private Properties getSfProps() {
        Properties props = new Properties();
        System.out.println("props");
        props.setProperty("hibernate.dialect", dbDialect);
        props.setProperty("hibernate.show_sql", "true");
        props.setProperty("hibernate.format_sql", "true");
        props.setProperty("hibernate.hbm2ddl.auto", "none");

        return props;
    }
}
