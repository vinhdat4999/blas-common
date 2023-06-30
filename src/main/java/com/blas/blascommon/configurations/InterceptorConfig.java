package com.blas.blascommon.configurations;

import com.blas.blascommon.core.service.BlasGateInfoService;
import com.blas.blascommon.core.service.CentralizedLogService;
import com.blas.blascommon.interceptors.BlasGateInterceptor;
import com.blas.blascommon.jwt.JwtTokenUtil;
import com.blas.blascommon.properties.BlasGateConfiguration;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import com.blas.blascommon.properties.ServiceSupportProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

  @Lazy
  @Autowired
  private BlasServiceConfiguration blasServiceConfiguration;

  @Lazy
  @Autowired
  private BlasGateConfiguration blasGateConfiguration;

  @Lazy
  @Autowired
  private BlasGateInfoService blasGateInfoService;

  @Lazy
  @Autowired
  private ServiceSupportProperties serviceSupportProperties;

  @Lazy
  @Autowired
  private CentralizedLogService centralizedLogService;

  @Lazy
  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Value("${blas.blas-idp.isSendEmailAlert}")
  private boolean isSendEmailAlert;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new BlasGateInterceptor(blasServiceConfiguration, blasGateConfiguration,
        blasGateInfoService, serviceSupportProperties, centralizedLogService, isSendEmailAlert,
        jwtTokenUtil));
  }
}
