package com.blas.blascommon.configurations;

import com.blas.blascommon.core.service.BlasGateInfoService;
import com.blas.blascommon.interceptors.BlasGateInterceptor;
import com.blas.blascommon.properties.BlasGateConfiguration;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new BlasGateInterceptor(blasServiceConfiguration, blasGateConfiguration,
        blasGateInfoService));
  }
}
