package com.blas.blascommon.utils.httprequest;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostRequest {

  private PostRequest() {
  }

  public static String sendPostRequestWithJsonObjectPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload) {
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
      HttpPost httpPost = new HttpPost(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPost.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPost.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response = IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendPostRequestWithJsonObjectPayloadGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload) {
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
      HttpPost httpPost = new HttpPost(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPost.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPost.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response = new JSONObject(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONArray sendPostRequestWithJsonObjectPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload) {
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
      HttpPost httpPost = new HttpPost(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPost.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPost.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response = new JSONArray(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static String sendPostRequestWithJsonArrayPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload) {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
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
      HttpPost httpPost = new HttpPost(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPost.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPost.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response = IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendPostRequestWithJsonArrayPayloadGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload) {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
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
      HttpPost httpPost = new HttpPost(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPost.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPost.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response = new JSONObject(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONArray sendPostRequestWithJsonArrayPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload) {
    payload = new JSONArray(
        payload.toString().replace("\\", "").replace("[\"", "[").replace("\"]", "]")
            .replace("}\"", "}").replace("\"{", "{"));
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
      HttpPost httpPost = new HttpPost(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpPost.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      StringEntity entity = new StringEntity(payload.toString(), ContentType.APPLICATION_JSON);
      CloseableHttpClient httpClient = HttpClientBuilder.create().build();
      httpPost.setEntity(entity);
      CloseableHttpResponse response2 = httpClient.execute(httpPost);
      response = new JSONArray(
          IOUtils.toString(response2.getEntity().getContent(), StandardCharsets.UTF_8));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendPostRequestWithFormUrlEncodedPayloadGetJsonObjectResponse(
      String hostUrl, Map<String, String> parameterList, Map<String, String> headerList,
      Map<String, String> payload) {
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
    HttpPost httpPost = new HttpPost(urlEndpoint);
    if (headerList != null) {
      for (String headerKey : headerList.keySet()) {
        httpPost.setHeader(headerKey, headerList.get(headerKey));
      }
    }
    List<NameValuePair> urlParameters = new ArrayList<>();
    for (String payloadKey : payload.keySet()) {
      String payloadValue = payload.get(payloadKey);
      urlParameters.add(new BasicNameValuePair(payloadKey, payloadValue));
    }
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse res = httpClient.execute(
        httpPost);) {
      response = new JSONObject(EntityUtils.toString(res.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static String sendPostRequestWithFormUrlEncodedPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList,
      Map<String, String> payload) {
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
    HttpPost httpPost = new HttpPost(urlEndpoint);
    if (headerList != null) {
      for (String headerKey : headerList.keySet()) {
        httpPost.setHeader(headerKey, headerList.get(headerKey));
      }
    }
    List<NameValuePair> urlParameters = new ArrayList<>();
    for (String payloadKey : payload.keySet()) {
      String payloadValue = payload.get(payloadKey);
      urlParameters.add(new BasicNameValuePair(payloadKey, payloadValue));
    }
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse res = httpClient.execute(
        httpPost);) {

      response = EntityUtils.toString(res.getEntity());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }
}