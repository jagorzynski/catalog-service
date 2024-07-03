package com.polarbookshop.catalogservice.config;

import java.util.Optional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

@Configuration
public class JpaConfig {
  @Bean
  public AuditorAware<String> auditorProvider() {
    // Provide the current auditor (e.g., username)
    return () -> Optional.ofNullable("currentAuditor");
  }
}
