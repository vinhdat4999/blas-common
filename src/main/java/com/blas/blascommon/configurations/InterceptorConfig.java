package com.blas.blascommon.configurations;

import com.blas.blascommon.core.service.BlasGateInfoService;
import com.blas.blascommon.core.service.CentralizedLogService;
import com.blas.blascommon.interceptors.BlasGateInterceptor;
import com.blas.blascommon.jwt.JwtTokenUtil;
import com.blas.blascommon.properties.BlasGateConfiguration;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import com.blas.blascommon.properties.ServiceSupportProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

  @Lazy
  private final BlasServiceConfiguration blasServiceConfiguration;

  @Lazy
  private final BlasGateConfiguration blasGateConfiguration;

  @Lazy
  private final BlasGateInfoService blasGateInfoService;

  @Lazy
  private final ServiceSupportProperties serviceSupportProperties;

  @Lazy
  private final CentralizedLogService centralizedLogService;

  @Lazy
  private final JwtTokenUtil jwtTokenUtil;

  @Value("${blas.blas-idp.isSendEmailAlert}")
  private boolean isSendEmailAlert;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new BlasGateInterceptor(blasServiceConfiguration, blasGateConfiguration,
        blasGateInfoService, serviceSupportProperties, centralizedLogService, isSendEmailAlert,
        jwtTokenUtil));
  }
}
