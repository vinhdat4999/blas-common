package com.blas.blascommon.interceptors;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.equalsAny;

import com.blas.blascommon.core.model.BlasGateInfo;
import com.blas.blascommon.core.service.BlasGateInfoService;
import com.blas.blascommon.properties.BlasGateConfiguration;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class BlasGateInterceptor implements HandlerInterceptor {

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
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    if (blasGateConfiguration.isEnableLogRequest() && (!isLocalRequest(request)
        || blasGateConfiguration.isEnableLogLocalRequest())) {
      BlasGateInfo blasGateInfo = BlasGateInfo.builder()
          .service(blasServiceConfiguration.getServiceName())
          .timeLogged(now())
          .locale(request.getLocale().toString())
          .remoteUser(request.getRemoteUser())
          .remoteAddress(request.getRemoteAddr())
          .requestUrl(request.getRequestURL().toString())
          .remotePort(String.valueOf(request.getRemotePort()))
          .queryString(request.getQueryString())
          .build();
      blasGateInfoService.createBlasGateInfo(blasGateInfo);
    }
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // No action to check
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler,
      Exception ex) throws Exception {
    // No action to check
  }

  private boolean isLocalRequest(HttpServletRequest request) {
    return equalsAny(request.getRemoteAddr(), "127.0.0.1", "0:0:0:0:0:0:0:1");
  }
}
