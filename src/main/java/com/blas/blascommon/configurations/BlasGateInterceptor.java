package com.blas.blascommon.configurations;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.lang.NonNull;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@EnableAsync
@Configuration
@RequiredArgsConstructor
public class BlasGateInterceptor implements HandlerInterceptor, WebMvcConfigurer {

  @Lazy
  private final MaintenanceConfiguration maintenanceConfiguration;

  @Lazy
  private final RequestInterceptorConfiguration requestInterceptorConfiguration;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(this);
  }

  @Override
  public boolean preHandle(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull Object handler) {
    requestInterceptorConfiguration.logRequestInfo(request);
    maintenanceConfiguration.checkMaintenance(request);
    return true;
  }

  @Override
  public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
      @NonNull Object handler, ModelAndView modelAndView) {
    // No operation
  }

  @Override
  public void afterCompletion(@NonNull HttpServletRequest request,
      @NonNull HttpServletResponse response, @NonNull Object handler, Exception ex) {
    // No operation
  }
}
