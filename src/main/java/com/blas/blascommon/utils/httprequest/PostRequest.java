package com.blas.blascommon.utils.httprequest;

import static com.blas.blascommon.utils.StringUtils.AMPERSAND;
import static com.blas.blascommon.utils.StringUtils.EQUAL;
import static com.blas.blascommon.utils.StringUtils.QUESTION_MARK;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

@UtilityClass
public class PostRequest {

  public static String sendPostRequestWithJsonObjectPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONObject sendPostRequestWithJsonObjectPayloadGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetJsonObjectResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONArray sendPostRequestWithJsonObjectPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException {
    return sendRequestGetJsonArrayResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static String sendPostRequestWithJsonArrayPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    return sendRequestGetStringResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONObject sendPostRequestWithJsonArrayPayloadGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    return sendRequestGetJsonObjectResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONArray sendPostRequestWithJsonArrayPayloadGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException {
    return sendRequestGetJsonArrayResponse(hostUrl, parameterList, headerList, payload.toString());
  }

  public static JSONObject sendPostRequestWithFormUrlEncodedPayloadGetJsonObjectResponse(
      String hostUrl, Map<String, String> parameterList, Map<String, String> headerList,
      Map<String, String> payload) throws IOException {
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      CloseableHttpResponse res = client.execute(
          genHttpPost(hostUrl, parameterList, headerList, payload));
      return new JSONObject(EntityUtils.toString(res.getEntity()));
    }
  }

  public static String sendPostRequestWithFormUrlEncodedPayloadGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList,
      Map<String, String> payload) throws IOException {
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      CloseableHttpResponse res = client.execute(
          genHttpPost(hostUrl, parameterList, headerList, payload));
      return EntityUtils.toString(res.getEntity());
    }
  }

  private static String buildUrlEndpoint(String hostUrl, Map<String, String> parameterList) {
    if (parameterList != null) {
      StringBuilder sb = new StringBuilder();
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append(EQUAL).append(entry.getValue()).append(AMPERSAND);
      }
      hostUrl += QUESTION_MARK + sb.substring(0, sb.toString().length() - 1);
    }
    return hostUrl;
  }

  private static String sendRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    HttpPost httpPost = new HttpPost(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    httpPost.setEntity(entity);
    CloseableHttpResponse response2 = httpClient.execute(httpPost);
    return IOUtils.toString(response2.getEntity().getContent(), UTF_8);
  }

  private static JSONObject sendRequestGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    HttpPost httpPost = new HttpPost(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    httpPost.setEntity(entity);
    CloseableHttpResponse response2 = httpClient.execute(httpPost);
    return new JSONObject(
        IOUtils.toString(response2.getEntity().getContent(), UTF_8));
  }

  private static JSONArray sendRequestGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException {
    HttpPost httpPost = new HttpPost(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(payload, APPLICATION_JSON);
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    httpPost.setEntity(entity);
    CloseableHttpResponse response2 = httpClient.execute(httpPost);
    return new JSONArray(
        IOUtils.toString(response2.getEntity().getContent(), UTF_8));
  }

  private static HttpPost genHttpPost(String hostUrl, Map<String, String> parameterList,
      Map<String, String> headerList, Map<String, String> payload)
      throws UnsupportedEncodingException {
    HttpPost httpPost = new HttpPost(buildUrlEndpoint(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpPost.setHeader(entry.getKey(), entry.getValue());
      }
    }
    List<NameValuePair> urlParameters = new ArrayList<>();
    for (Entry<String, String> entry : payload.entrySet()) {
      urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
    }
    httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
    return httpPost;
  }
}
