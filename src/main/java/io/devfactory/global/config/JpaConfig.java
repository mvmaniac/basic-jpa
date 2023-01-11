package io.devfactory.global.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@EnableJpaAuditing
@Configuration
public class JpaConfig {

  @PersistenceContext(unitName = "mysqlEntityManager")
  private EntityManager mysqlEntityManager;

  @PersistenceContext(unitName = "postgresqlEntityManager")
  private EntityManager postgresqlEntityManager;

  @Bean("mysqlJpaQueryFactory")
  public JPAQueryFactory mysqlJpaQueryFactory() {
    return new JPAQueryFactory(mysqlEntityManager);
  }

  @Bean("postgresqlJpaQueryFactory")
  public JPAQueryFactory postgresqlJpaQueryFactory() {
    return new JPAQueryFactory(postgresqlEntityManager);
  }

}
