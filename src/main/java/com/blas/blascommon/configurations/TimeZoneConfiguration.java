package com.blas.blascommon.configurations;

import jakarta.annotation.PostConstruct;
import java.util.TimeZone;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeZoneConfiguration {

  @PostConstruct
  public void init() {
    TimeZone.setDefault(TimeZone.getTimeZone("GMT+7"));
  }
}
