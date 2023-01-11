package io.devfactory.global.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.devfactory.global.common.annotation.PostgresqlRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import jakarta.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@EnableJpaRepositories(
    basePackages = "io.devfactory.web", includeFilters = @ComponentScan.Filter(PostgresqlRepository.class),
    entityManagerFactoryRef = "postgresqlEntityManagerFactory", transactionManagerRef = "postgresqlTransactionManager"
)
@Configuration
public class PostgresqlDataSourceConfig extends DataSourceConfigSupport {

  public static final String ENTITY_PACKAGE_NAME = "io.devfactory.web.**.postgresql";

  @ConfigurationProperties(prefix = "spring.datasource.hikari.postgresql")
  @Bean
  public HikariConfig postgresqlHikariConfig() {
    return new HikariConfig();
  }

  @Bean
  public DataSource postgresqlDataSource() {
    return new HikariDataSource(postgresqlHikariConfig());
  }

  @Bean
  @DependsOn("postgresqlDataSourceInitializer")
  public LocalContainerEntityManagerFactoryBean postgresqlEntityManagerFactory(
      @Qualifier("postgresqlDataSource") DataSource postgresqlDataSource) {
    return buildEntityManagerFactory("postgresqlEntityManager", postgresqlDataSource,
        buildJpaProperties(jpaProperties(), hibernateProperties()), ENTITY_PACKAGE_NAME);
  }

  @Bean
  public PlatformTransactionManager postgresqlTransactionManager(
      @Qualifier("postgresqlEntityManagerFactory") EntityManagerFactory postgresqlEntityManagerFactory) {
    return buildTransactionManager(postgresqlEntityManagerFactory);
  }

  @Bean
  public DataSourceInitializer postgresqlDataSourceInitializer(
      @Qualifier("postgresqlDataSource") DataSource postgresqlDataSource,
      @Qualifier("postgresqlSqlInitProperty") SqlInitProperties.SqlInitProperty postgresqlSqlInitProperty) {
    return super.buildDataSourceInitializer(postgresqlDataSource, postgresqlSqlInitProperty.toClassPathResources());
  }

}
