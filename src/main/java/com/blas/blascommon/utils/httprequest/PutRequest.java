package com.blas.blascommon.utils.httprequest;

import com.blas.blascommon.payload.HttpResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.json.JSONArray;
import org.json.JSONObject;

@UtilityClass
public class PutRequest {

  public static HttpResponse sendPutRequestWithStringPayload(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload);
  }

  public static HttpResponse sendPutRequestWithJsonObjectPayload(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static HttpResponse sendPutRequestWithJsonArrayPayload(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  private static HttpResponse sendRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    if (parameterList != null) {
      StringBuilder sb = new StringBuilder();
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      hostUrl += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    HttpPut httpPut = new HttpPut(hostUrl);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPut.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClients.custom()
        .setRedirectStrategy(new LaxRedirectStrategy()).build();
    httpPut.setEntity(entity);
    CloseableHttpResponse response = httpClient.execute(httpPut);
    return HttpResponse.builder()
        .statusCode(response.getStatusLine().getStatusCode())
        .response(IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8))
        .build();
  }
}
