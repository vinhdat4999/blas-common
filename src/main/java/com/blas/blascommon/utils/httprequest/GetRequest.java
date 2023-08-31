package com.blas.blascommon.utils.httprequest;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.thymeleaf.util.StringUtils.substring;

import com.blas.blascommon.payload.HttpResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Slf4j
@UtilityClass
public class GetRequest {

  public static HttpResponse sendGetRequest(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) throws IOException {
    log.debug("Start send GET request...");
    log.debug("Connecting to {}...", hostUrl);
    HttpGet httpGet = new HttpGet(addParameters(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpGet.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      org.apache.http.HttpResponse httpResponse = client.execute(httpGet);
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      log.debug("Received GET response. HTTP status: {}", statusCode);
      return HttpResponse.builder()
          .statusCode(statusCode)
          .response(IOUtils.toString(httpResponse.getEntity().getContent(), UTF_8))
          .build();
    }
  }

  static String addParameters(String hostUrl, Map<String, String> parameterList) {
    if (parameterList != null) {
      StringBuilder parameters = new StringBuilder();
      for (Entry<String, String> entry : parameterList.entrySet()) {
        parameters.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      hostUrl += "?" + substring(parameters, 0, parameters.length() - 1);
    }
    return hostUrl;
  }
}
