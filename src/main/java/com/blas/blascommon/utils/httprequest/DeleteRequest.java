package com.blas.blascommon.utils.httprequest;

import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.payload.HttpResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@UtilityClass
public class DeleteRequest {

  public static HttpResponse sendDeleteRequest(String hostUrl, Map<String, String> parameterList,
      Map<String, String> headerList) throws IOException {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder();
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    String response;
    HttpDelete httpDelete = new HttpDelete(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpDelete.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      org.apache.http.HttpResponse httpResponse = client.execute(httpDelete);
      return HttpResponse.builder()
          .statusCode(httpResponse.getStatusLine().getStatusCode())
          .response(IOUtils.toString(httpResponse.getEntity().getContent(), UTF_8))
          .build();
    }
  }
}
