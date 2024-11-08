package com.blas.blascommon.core.service;

import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_SERVER_ERROR;
import static org.springframework.boot.actuate.endpoint.web.WebEndpointResponse.STATUS_OK;

import com.blas.blascommon.configurations.ObjectMapperConfiguration;
import com.blas.blascommon.core.service.http.HttpRequest;
import com.blas.blascommon.dto.ImgbbResponseDto;
import com.blas.blascommon.exceptions.types.BlasException;
import com.blas.blascommon.payload.HttpResponse;
import com.blas.blascommon.properties.ImgbbProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "blas.image.imgbb.enabled", havingValue = "true")
public class ImgbbService {

  private static final String HAVE_ERROR_WHEN_PUBLISH_QR_PAY_IMAGE_TO_IMGBB = "HAVE ERROR WHEN PUBLISH QR PAY IMAGE TO IMGBB";
  private static final String EXPIRATION = "expiration";
  private static final String KEY = "key";
  private static final String IMAGE = "image";

  @Lazy
  private final HttpRequest httpRequest;

  @Lazy
  private final ImgbbProperties imgbbProperties;

  @Lazy
  private final ObjectMapperConfiguration objectMapperConfiguration;

  @Lazy
  private final CentralizedLogService centralizedLogService;

  public String publishImage(String base64EncodedData)
      throws IOException, IllegalArgumentException {
    long expirationTime = imgbbProperties.getExpirationTime();
    HttpResponse response = httpRequest.sendPostRequestWithFormUrlEncodedPayload(
        imgbbProperties.getUrl(), Map.of(EXPIRATION, String.valueOf(expirationTime), KEY,
            imgbbProperties.getPrivateKey()), null, Map.of(IMAGE, base64EncodedData));

    ImgbbResponseDto imgbbResponseDTO = handleResponse(response);
    if (imgbbResponseDTO == null) {
      BlasException blasException = new BlasException(MSG_SERVER_ERROR,
          HAVE_ERROR_WHEN_PUBLISH_QR_PAY_IMAGE_TO_IMGBB);
      centralizedLogService.saveLog(blasException, response, imgbbProperties.getUrl(),
          expirationTime);
      throw blasException;
    }
    return imgbbResponseDTO.getData().getDisplayUrl();
  }

  private ImgbbResponseDto handleResponse(HttpResponse response) throws JsonProcessingException {
    if (!response.getStatusCode().equals(STATUS_OK)) {
      return null;
    }
    return objectMapperConfiguration.objectMapper()
        .readValue(response.getResponse(), ImgbbResponseDto.class);
  }
}
