package io.devfactory.global.config;

import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceConfigSupport {

  @ConfigurationProperties(prefix = "spring.jpa")
  @Bean
  public JpaProperties jpaProperties() {
    return new JpaProperties();
  }

  @ConfigurationProperties(prefix = "spring.jpa.hibernate")
  @Bean
  public HibernateProperties hibernateProperties() {
    return new HibernateProperties();
  }

  protected Properties buildJpaProperties(JpaProperties jpaProperties,
      HibernateProperties hibernateProperties) {
    final var hibernatePropertiesMap = hibernateProperties.determineHibernateProperties(
        jpaProperties.getProperties(), new HibernateSettings());

    final var properties = new Properties();
    properties.putAll(hibernatePropertiesMap);

    return properties;
  }

  protected LocalContainerEntityManagerFactoryBean buildEntityManagerFactory(
      String persistenceUnitName, DataSource dataSource, Properties properties,
      String... packageName) {
    final var jpaVendorAdapter = new HibernateJpaVendorAdapter();

    final var localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
    localContainerEntityManagerFactoryBean.setPersistenceUnitName(persistenceUnitName);
    localContainerEntityManagerFactoryBean.setDataSource(dataSource);
    localContainerEntityManagerFactoryBean.setJpaProperties(properties);
    localContainerEntityManagerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
    localContainerEntityManagerFactoryBean.setPackagesToScan(packageName);

    return localContainerEntityManagerFactoryBean;
  }

  protected PlatformTransactionManager buildTransactionManager(
      EntityManagerFactory entityManagerFactory) {
    return new JpaTransactionManager(entityManagerFactory);
  }

  protected DataSourceInitializer buildDataSourceInitializer(DataSource dataSource,
      ClassPathResource... resources) {
    final var dataSourceInitializer = new DataSourceInitializer();
    dataSourceInitializer.setDataSource(dataSource);
    dataSourceInitializer.setDatabasePopulator(new ResourceDatabasePopulator(resources));
    return dataSourceInitializer;
  }

}
