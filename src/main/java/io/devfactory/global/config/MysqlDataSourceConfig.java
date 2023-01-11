package io.devfactory.global.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.devfactory.global.common.annotation.MysqlRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableJpaRepositories(
    basePackages = "io.devfactory.web", includeFilters = @ComponentScan.Filter(MysqlRepository.class),
    entityManagerFactoryRef = "mysqlEntityManagerFactory", transactionManagerRef = "mysqlTransactionManager"
)
@Configuration
public class MysqlDataSourceConfig extends DataSourceConfigSupport {

  public static final String ENTITY_PACKAGE_NAME = "io.devfactory.web.**.mysql";

  @ConfigurationProperties(prefix = "spring.datasource.hikari.mysql")
  @Bean
  public HikariConfig mysqlHikariConfig() {
    return new HikariConfig();
  }

  @Primary
  @Bean
  public DataSource mysqlDataSource() {
    return new HikariDataSource(mysqlHikariConfig());
  }

  @Primary
  @Bean
  @DependsOn("mysqlDataSourceInitializer")
  public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
      @Qualifier("mysqlDataSource") DataSource mysqlDataSource) {
    return buildEntityManagerFactory("mysqlEntityManager", mysqlDataSource,
        buildJpaProperties(jpaProperties(), hibernateProperties()), ENTITY_PACKAGE_NAME);
  }

  @Primary
  @Bean
  public PlatformTransactionManager mysqlTransactionManager(
      @Qualifier("mysqlEntityManagerFactory") EntityManagerFactory mysqlEntityManagerFactory) {
    return buildTransactionManager(mysqlEntityManagerFactory);
  }

  @Bean
  public DataSourceInitializer mysqlDataSourceInitializer(
      @Qualifier("mysqlDataSource") DataSource mysqlDataSource,
      @Qualifier("mysqlSqlInitProperty") SqlInitProperties.SqlInitProperty mysqlSqlInitProperty) {
    return super.buildDataSourceInitializer(mysqlDataSource, mysqlSqlInitProperty.toClassPathResources());
  }

}
