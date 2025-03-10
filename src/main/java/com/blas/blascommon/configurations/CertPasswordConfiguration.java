package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.BlasConstant.BLAS_CERT_PASSWORD;
import static com.blas.blascommon.security.SecurityUtils.base64Decode;

import com.blas.blascommon.core.service.BlasConfigService;
import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class CertPasswordConfiguration {

  private final BlasConfigService blasConfigService;

  @Bean
  public String getCertPassword() {
    log.debug("Getting cert password...");
    return new String(base64Decode(blasConfigService.getConfigValueFromKey(BLAS_CERT_PASSWORD)),
        StandardCharsets.UTF_8);
  }
}
