package com.blas.blascommon.configurations;

import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_IN_MAINTENANCE;
import static com.blas.blascommon.utils.httprequest.HttpMethod.GET;
import static com.blas.blascommon.utils.httprequest.RequestUtils.getTokenFromRequest;

import com.blas.blascommon.exceptions.types.MaintenanceException;
import com.blas.blascommon.payload.HttpResponse;
import com.blas.blascommon.payload.MaintenanceTimeResponse;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import com.blas.blascommon.properties.ServiceSupportProperties;
import com.blas.blascommon.utils.httprequest.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class MaintenanceConfiguration {

  @Lazy
  private final HttpRequest httpRequest;

  @Lazy
  private final BlasServiceConfiguration blasServiceConfiguration;

  @Lazy
  private final ServiceSupportProperties serviceSupportProperties;

  @Lazy
  private final ObjectMapper objectMapper;

  public void checkMaintenance(HttpServletRequest request) {
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
    HttpResponse response;
    try {
      response = httpRequest.sendRequestWithoutRequestBody(
          serviceSupportProperties.getEndpointCheckMaintenance(), GET,
          Map.of("service", serviceName), getTokenFromRequest(request));
      if (response.getStatusCode() != HttpStatus.OK.value()) {
        log.warn("Can not check maintenance time for " + serviceName
            + " properly. Skip checking maintenance time.");
      } else {
        maintenanceTimeResponse = objectMapper.readValue(
            response.getResponse(),
            MaintenanceTimeResponse.class);
        isChecked = true;
      }
    } catch (Exception exception) {
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
}
