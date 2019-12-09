package com.jun.spitter.config;

import com.jun.spitter.model.Spitter;
import com.jun.spitter.repository.db.hibernate4.HibernateSpitterRepository;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.inject.Inject;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackageClasses = {HibernateSpitterRepository.class, Spitter.class})
public class Hibernate4RepositoryConfig implements TransactionManagementConfigurer {
    @Inject
    private SessionFactory sessionFactory;

    @Bean
    public DataSource dataSource(){
        EmbeddedDatabaseBuilder databaseBuilder = new EmbeddedDatabaseBuilder();
        databaseBuilder.setType(EmbeddedDatabaseType.H2);
        databaseBuilder.addScript("classpath:schema.sql");
        databaseBuilder.addScript("classpath:test-data.sql");
        EmbeddedDatabase embeddedDatabase = databaseBuilder.build();
        return embeddedDatabase;
    }

    @Bean
    public SessionFactory sessionFactory(){
        try {
            LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
            localSessionFactoryBean.setDataSource(dataSource());
            localSessionFactoryBean.setPackagesToScan("com.jun.spitter.model");
            Properties properties = new Properties();
            properties.setProperty("dialect","org.hibernate.dialect.H2Dialect");
            localSessionFactoryBean.setHibernateProperties(properties);
            localSessionFactoryBean.afterPropertiesSet();
            SessionFactory sessionFactory= localSessionFactoryBean.getObject();
            return sessionFactory;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }
}
