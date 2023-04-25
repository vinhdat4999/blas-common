package com.blas.blascommon.core.model;

import static com.blas.blascommon.constants.BlasConstant.BLAS_CERT_PASSWORD;
import static com.blas.blascommon.security.SecurityUtils.base64Decode;

import com.blas.blascommon.core.service.BlasConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyConfiguration {

  @Autowired
  private BlasConfigService blasConfigService;

  @Bean
  public String getCertPassword() {
    return new String(base64Decode(blasConfigService.getConfigValueFromKey(BLAS_CERT_PASSWORD)));
  }
}
