package com.dev.sbauth.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Connection;
import java.sql.SQLException;

@Configuration
@EnableJpaRepositories(basePackages = "com.dev.sbauth.repository")
@EntityScan(basePackages = "com.dev.sbauth.entity")
public class JpaConfig {

    @Value("${spring.datasource.url}")
    private String dataSourceUrl;

    @Value("${spring.datasource.username}")
    private String dataSourceUsername;

    @Value("${spring.datasource.password}")
    private String dataSourcePassword;

    private final DataSource dataSource;

    public JpaConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @PostConstruct
    public void checkDatabaseConnection() {
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("Connected to the database successfully!");
            System.out.println("Database URL: " + connection.getMetaData().getURL());
        } catch (SQLException e) {
            System.err.println("Failed to connect to the database!");
            e.printStackTrace();
        }
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("com.dev.sbauth.entity");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        System.out.println("DataSource URL: " + dataSourceUrl);
        System.out.println("DataSource Username: " + dataSourceUsername);
        return em;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }
}

