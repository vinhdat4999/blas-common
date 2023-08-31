package com.blas.blascommon.utils.httprequest;

import static com.blas.blascommon.utils.httprequest.GetRequest.addParameters;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.payload.HttpResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@Slf4j
@UtilityClass
public class DeleteRequest {

  public static HttpResponse sendDeleteRequest(String hostUrl, Map<String, String> parameterList,
      Map<String, String> headerList) throws IOException {
    log.debug("Start send DELETE request...");
    log.debug("Connecting to {}...", hostUrl);
    hostUrl = addParameters(hostUrl, parameterList);
    HttpDelete httpDelete = new HttpDelete(hostUrl);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpDelete.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      org.apache.http.HttpResponse httpResponse = client.execute(httpDelete);
      int statusCode = httpResponse.getStatusLine().getStatusCode();
      log.debug("Received DELETE response. HTTP status: {}", statusCode);
      return HttpResponse.builder()
          .statusCode(statusCode)
          .response(IOUtils.toString(httpResponse.getEntity().getContent(), UTF_8))
          .build();
    }
  }
}
