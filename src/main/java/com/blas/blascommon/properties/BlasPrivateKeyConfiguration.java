package com.blas.blascommon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blas")
public class BlasPrivateKeyConfiguration {

  private String aliasBlasPrivateKey;
  private String certificate;
}
