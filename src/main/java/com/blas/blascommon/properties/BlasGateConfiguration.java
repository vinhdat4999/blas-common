package com.blas.blascommon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blas.gate-interceptor")
public class BlasGateConfiguration {

  private boolean enableLogRequest;
  private boolean enableLogLocalRequest;
}
