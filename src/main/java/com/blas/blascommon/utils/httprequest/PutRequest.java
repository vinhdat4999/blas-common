package com.blas.blascommon.utils.httprequest;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class PutRequest {

  private PutRequest() {
  }

  public static String sendPutRequestWithJsonObjectPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList, JSONObject payload) {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    String response = null;
    try {
      HttpPut httpPut = new HttpPut(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPut.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(),
          ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPut.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPut);
      response = IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendPutRequestWithJsonObjectPayloadGetJsonObjectResponse(
      String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList, JSONObject payload) {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    JSONObject response = null;
    try {
      HttpPut httpPut = new HttpPut(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPut.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(),
          ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPut.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPut);
      response = new JSONObject(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONArray sendPutRequestWithJsonObjectPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList, JSONObject payload) {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    JSONArray response = null;
    try {
      HttpPut httpPut = new HttpPut(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPut.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(),
          ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPut.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPut);
      response = new JSONArray(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static String sendPutRequestWithJsonArrayPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList, JSONArray payload) {
    payload = new JSONArray(payload.toString().replace("\\", "").replace("[\"", "[")
        .replace("\"]", "]").replace("}\"", "}").replace("\"{", "{"));
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    String response = null;
    try {
      HttpPut httpPut = new HttpPut(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPut.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(),
          ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPut.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPut);
      response = IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendPutRequestWithJsonArrayPayloadGetJsonObjectResponse(
      String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList, JSONArray payload) {
    payload = new JSONArray(payload.toString().replace("\\", "").replace("[\"", "[")
        .replace("\"]", "]").replace("}\"", "}").replace("\"{", "{"));
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    JSONObject response = null;
    try {
      HttpPut httpPut = new HttpPut(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPut.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(),
          ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPut.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPut);
      response = new JSONObject(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONArray sendPutRequestWithJsonArrayPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList, JSONArray payload) {
    payload = new JSONArray(payload.toString().replace("\\", "").replace("[\"", "[")
        .replace("\"]", "]").replace("}\"", "}").replace("\"{", "{"));
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    JSONArray response = null;
    try {
      HttpPut httpPut = new HttpPut(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPut.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(),
          ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPut.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPut);
      response = new JSONArray(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }
}