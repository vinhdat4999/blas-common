package com.blas.blascommon.core.configuration;

import static com.blas.blascommon.constants.BlasConstant.BLAS_CERT_PASSWORD;
import static com.blas.blascommon.security.SecurityUtils.base64Decode;

import com.blas.blascommon.core.service.BlasConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeyConfiguration {

  @Bean
  public String getCertPassword(BlasConfigService blasConfigService) {
    return new String(base64Decode(blasConfigService.getConfigValueFromKey(BLAS_CERT_PASSWORD)));
  }
}
