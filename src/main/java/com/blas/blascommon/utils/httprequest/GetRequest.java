package com.blas.blascommon.utils.httprequest;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

@UtilityClass
public class GetRequest {

  public static String sendGetRequest(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) throws IOException {
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder();
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      hostUrl += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    HttpGet httpGet = new HttpGet(hostUrl);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpGet.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      HttpResponse httpResponse = client.execute(httpGet);
      return IOUtils.toString(httpResponse.getEntity().getContent(), UTF_8);
    }
  }
}
