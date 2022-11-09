package com.blas.blascommon.utils.httprequest;

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
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

@UtilityClass
public class PutRequest {

  public static String sendPutRequestWithJsonObjectPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONObject sendPutRequestWithJsonObjectPayloadGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetJsonObjectResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONArray sendPutRequestWithJsonObjectPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetJsonArrayResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static String sendPutRequestWithJsonArrayPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONObject sendPutRequestWithJsonArrayPayloadGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
    return sendRequestGetJsonObjectResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONArray sendPutRequestWithJsonArrayPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
    return sendRequestGetJsonArrayResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  private static String buildUrlEndpoint(String hostUrl, Map<String, String> parameterList) {
    if (parameterList != null) {
      StringBuilder sb = new StringBuilder("");
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      hostUrl += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    return hostUrl;
  }

  private static String sendRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    HttpPut httpPut = new HttpPut(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPut.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    httpPut.setEntity(entity);
    CloseableHttpResponse response2 = httpClient.execute(httpPut);
    return IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8);
  }

  private static JSONObject sendRequestGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    HttpPut httpPut = new HttpPut(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPut.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    httpPut.setEntity(entity);
    CloseableHttpResponse response2 = httpClient.execute(httpPut);
    return new JSONObject(
        IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
  }

  private static JSONArray sendRequestGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    HttpPut httpPut = new HttpPut(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPut.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    httpPut.setEntity(entity);
    CloseableHttpResponse response2 = httpClient.execute(httpPut);
    return new JSONArray(
        IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
  }
}
