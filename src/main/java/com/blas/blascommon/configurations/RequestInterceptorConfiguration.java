package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.MdcConstants.GLOBAL_ID;
import static com.blas.blascommon.utils.IpUtils.isLocalRequest;
import static com.blas.blascommon.utils.idutils.IdUtils.genUniqueId;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.springframework.http.HttpHeaders.USER_AGENT;

import com.blas.blascommon.core.model.BlasGateInfo;
import com.blas.blascommon.core.service.BlasGateInfoService;
import com.blas.blascommon.properties.BlasGateProperties;
import com.blas.blascommon.properties.BlasServiceProperties;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class RequestInterceptorConfiguration {

  private static final List<String> REQUIRED_LOG_SERVICES = List.of("blas-payment-gateway",
      "blas-notification");

  @Lazy
  private final BlasGateProperties blasGateProperties;

  @Lazy
  private final BlasGateInfoService blasGateInfoService;

  @Lazy
  private final BlasServiceProperties blasServiceProperties;

  @Value("${blas.service.serviceName}")
  protected String serviceName;

  public void logRequestInfo(HttpServletRequest request) {
    if (REQUIRED_LOG_SERVICES.contains(serviceName) || (blasGateProperties.isEnableLogRequest()
        && (!isLocalRequest(request) || blasGateProperties.isEnableLogLocalRequest()))) {
      String globalId = MDC.get(GLOBAL_ID);
      if (isBlank(globalId)) {
        globalId = genUniqueId();
        MDC.put(GLOBAL_ID, globalId);
      }
      BlasGateInfo blasGateInfo = BlasGateInfo.builder()
          .globalId(globalId)
          .service(blasServiceProperties.getServiceName())
          .timeLogged(now())
          .locale(request.getLocale().toString())
          .remoteUser(request.getRemoteUser())
          .remoteAddress(request.getRemoteAddr())
          .requestUrl(request.getRequestURL().toString())
          .userAgent(request.getHeader(USER_AGENT))
          .queryString(request.getQueryString())
          .build();
      blasGateInfoService.createBlasGateInfo(blasGateInfo);
    }
  }
}
