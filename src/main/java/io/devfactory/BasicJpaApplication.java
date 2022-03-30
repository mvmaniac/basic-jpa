package io.devfactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan("io.devfactory.global.config")
@SpringBootApplication
public class BasicJpaApplication {

  public static void main(String[] args) {
    SpringApplication.run(BasicJpaApplication.class, args);
  }

}
