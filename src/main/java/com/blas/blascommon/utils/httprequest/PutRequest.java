package com.blas.blascommon.utils.httprequest;

import static com.blas.blascommon.utils.httprequest.GetRequest.addParameters;

import com.blas.blascommon.payload.HttpResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
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
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  private static HttpResponse sendRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    log.debug("Start send PUT request...");
    log.debug("Connecting to {}...", hostUrl);
    hostUrl = addParameters(hostUrl, parameterList);
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
    CloseableHttpResponse httpResponse = httpClient.execute(httpPut);
    int statusCode = httpResponse.getStatusLine().getStatusCode();
    log.debug("Received PUT response. HTTP status: {}", statusCode);
    return HttpResponse.builder()
        .statusCode(statusCode)
        .response(IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8))
        .build();
  }
}
