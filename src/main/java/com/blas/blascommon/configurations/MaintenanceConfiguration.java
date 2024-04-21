package com.blas.blascommon.configurations;

import static com.blas.blascommon.utils.httprequest.HttpMethod.GET;
import static com.blas.blascommon.utils.httprequest.RequestUtils.getTokenFromRequest;

import com.blas.blascommon.exceptions.BlasErrorCodeEnum;
import com.blas.blascommon.exceptions.types.MaintenanceException;
import com.blas.blascommon.payload.HttpResponse;
import com.blas.blascommon.payload.MaintenanceTimeResponse;
import com.blas.blascommon.properties.BlasServiceConfiguration;
import com.blas.blascommon.properties.ServiceSupportProperties;
import com.blas.blascommon.utils.httprequest.HttpRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
    if (serviceSkip.contains(serviceName) || !serviceSupportProperties.isThroughServiceSupport()) {
      log.debug("Service {} does not require maintenance check.", serviceName);
      return;
    }
    log.debug("Starting maintenance check for {}", serviceName);
    try {
      HttpResponse response = httpRequest.sendRequestWithoutRequestBody(
          serviceSupportProperties.getEndpointCheckMaintenance(), GET,
          Map.of("service", serviceName), getTokenFromRequest(request));

      if (response.getStatusCode() == HttpStatus.OK.value()) {
        MaintenanceTimeResponse maintenanceTimeResponse = objectMapper.readValue(
            response.getResponse(), MaintenanceTimeResponse.class);
        if (maintenanceTimeResponse.isInMaintenance()) {
          throw new MaintenanceException(BlasErrorCodeEnum.MSG_IN_MAINTENANCE,
              maintenanceTimeResponse);
        } else {
          log.debug("{} is available.", serviceName);
        }
      } else {
        log.warn("Unable to check maintenance time for {}. Skipping maintenance check.",
            serviceName);
      }
    } catch (IOException | InvocationTargetException | NoSuchMethodException |
             InstantiationException | IllegalAccessException exception) {
      log.warn("Error while checking maintenance time for {}. Skipping maintenance check.",
          serviceName);
    } finally {
      log.debug("Completed maintenance check for {}", serviceName);
    }
  }
}
