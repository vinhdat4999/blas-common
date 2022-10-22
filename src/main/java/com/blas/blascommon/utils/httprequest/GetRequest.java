package com.blas.blascommon.utils.httprequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetRequest {

  private GetRequest() {
  }

  public static String sendGetRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) throws IOException {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    String response = null;
    HttpGet httpGet = new HttpGet(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpGet.setHeader(entry.getKey(), entry.getValue());
      }
    }
    CloseableHttpClient client = HttpClients.createDefault();
    HttpResponse httpResponse = client.execute(httpGet);
    return IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
  }

  public static JSONObject sendGetRequestGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) throws IOException {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    JSONObject response = null;
    HttpGet httpGet = new HttpGet(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpGet.setHeader(entry.getKey(), entry.getValue());
      }
    }
    CloseableHttpClient client = HttpClients.createDefault();
    HttpResponse httpResponse = client.execute(httpGet);
    String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(),
        StandardCharsets.UTF_8);
    return new JSONObject(responseStr);
  }

  public static JSONArray sendGetRequestGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) throws IOException {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    JSONArray response = null;
    HttpGet httpGet = new HttpGet(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpGet.setHeader(entry.getKey(), entry.getValue());
      }
    }
    CloseableHttpClient client = HttpClients.createDefault();
    HttpResponse httpResponse = client.execute(httpGet);
    String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(),
        StandardCharsets.UTF_8);
    return new JSONArray(responseStr);
  }

  public static List<JSONObject> sendGetRequestGetListJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) throws IOException {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (Entry<String, String> entry : parameterList.entrySet()) {
        sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    List<JSONObject> jsonObjectList = new ArrayList<>();
    HttpGet httpGet = new HttpGet(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpGet.setHeader(entry.getKey(), entry.getValue());
      }
    }
    CloseableHttpClient client = HttpClients.createDefault();
    HttpResponse httpResponse = client.execute(httpGet);
    String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(),
        StandardCharsets.UTF_8);
    JSONArray data = new JSONArray(responseStr);
    for (int i = 0; i < data.length(); i++) {
      JSONObject jsonObject = data.getJSONObject(i);
      jsonObjectList.add(jsonObject);
    }
    return jsonObjectList;
  }
}