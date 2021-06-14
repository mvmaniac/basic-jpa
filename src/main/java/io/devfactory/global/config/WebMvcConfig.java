package io.devfactory.global.config;

import io.devfactory.global.common.resolver.SimplePageable;
import io.devfactory.global.common.resolver.SimplePageableMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(simplePageableMethodArgumentResolver());
  }

  @Bean
  public SimplePageableMethodArgumentResolver simplePageableMethodArgumentResolver() {
    final var resolver = new SimplePageableMethodArgumentResolver();
    resolver.setFallbackPageable(new SimplePageable());
    return resolver;
  }

}
