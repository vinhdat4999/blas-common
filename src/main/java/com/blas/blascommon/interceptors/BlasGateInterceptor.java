package com.blas.blascommon.interceptors;

import static com.blas.blascommon.constants.ResponseMessage.HTTP_STATUS_NOT_200;
import static com.blas.blascommon.enums.LogType.ERROR;
import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_IN_MAINTENANCE;
import static com.blas.blascommon.utils.IpUtils.isLocalRequest;
import static com.blas.blascommon.utils.httprequest.GetRequest.sendGetRequest;
import static java.time.LocalDateTime.now;

import com.blas.blascommon.core.model.BlasGateInfo;
import com.blas.blascommon.core.service.BlasGateInfoService;
import com.blas.blascommon.core.service.CentralizedLogService;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.MaintenanceException;
import com.blas.blascommon.jwt.JwtTokenUtil;
import com.blas.blascommon.payload.HttpResponse;
import com.blas.blascommon.payload.MaintenanceTimeResponse;
import com.blas.blascommon.properties.BlasGateConfiguration;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import com.blas.blascommon.properties.ServiceSupportProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@AllArgsConstructor
public class BlasGateInterceptor implements HandlerInterceptor {

  private BlasServiceConfiguration blasServiceConfiguration;
  private BlasGateConfiguration blasGateConfiguration;
  private BlasGateInfoService blasGateInfoService;
  private ServiceSupportProperties serviceSupportProperties;
  private CentralizedLogService centralizedLogService;
  private boolean isSendEmailAlert;
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    logRequestInfo(request);
    checkMaintenance();
    return true;
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) {
    // No action to check
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex) {
    // No action to check
  }

  private void checkMaintenance() {
    List<String> serviceSkip = List.of("blas-support-service", "blas-drive");
    String serviceName = blasServiceConfiguration.getServiceName();
    if (serviceSkip.contains(serviceName)) {
      return;
    }
    log.debug("Starting check maintenance...");
    if (!serviceSupportProperties.isThroughServiceSupport()) {
      log.debug(serviceName + " not through service support");
      log.debug("Completely check maintenance");
      return;
    }
    MaintenanceTimeResponse maintenanceTimeResponse = new MaintenanceTimeResponse();
    maintenanceTimeResponse.setInMaintenance(false);
    boolean isChecked = false;
    try {
      HttpResponse response = sendGetRequest(serviceSupportProperties.getEndpointCheckMaintenance(),
          Map.of("service", serviceName), jwtTokenUtil.generateInternalSystemToken());
      if (response.getStatusCode() != HttpStatus.OK.value()) {
        centralizedLogService.saveLog(serviceName, ERROR, null,
            HTTP_STATUS_NOT_200, response.toString(), null, null,
            String.valueOf(new JSONArray(new BadRequestException(HTTP_STATUS_NOT_200))),
            isSendEmailAlert);
      } else {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        maintenanceTimeResponse = objectMapper.readValue(
            response.getResponse(),
            MaintenanceTimeResponse.class);
        isChecked = true;
      }
    } catch (Exception ignored) {
      log.warn("Can not check maintenance time for " + serviceName
          + " properly. Skip checking maintenance time.");
    } finally {
      if (maintenanceTimeResponse.isInMaintenance()) {
        log.warn(serviceName + " unavailable");
      } else if (isChecked) {
        log.debug(serviceName + " available");
      }
      log.debug("Completely check maintenance");
    }
    if (maintenanceTimeResponse.isInMaintenance()) {
      throw new MaintenanceException(MSG_IN_MAINTENANCE, maintenanceTimeResponse);
    }
  }

  private void logRequestInfo(HttpServletRequest request) {
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
  }
}
