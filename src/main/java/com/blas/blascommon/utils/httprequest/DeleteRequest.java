package com.blas.blascommon.utils.httprequest;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeleteRequest {

  private DeleteRequest() {
  }

  public static String sendDeleteRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) {
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
    HttpDelete httpDelete = new HttpDelete(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpDelete.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault();) {
      HttpResponse httpResponse = client.execute(httpDelete);
      response = IOUtils.toString(httpResponse.getEntity().getContent(), StandardCharsets.UTF_8);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendDeleteRequestGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) {
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
    HttpDelete httpDelete = new HttpDelete(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpDelete.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault();) {
      HttpResponse httpResponse = client.execute(httpDelete);
      String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(),
          StandardCharsets.UTF_8);
      response = new JSONObject(responseStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONArray sendDeleteRequestGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) {
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
    HttpDelete httpDelete = new HttpDelete(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpDelete.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault();) {
      HttpResponse httpResponse = client.execute(httpDelete);
      String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(),
          StandardCharsets.UTF_8);
      response = new JSONArray(responseStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static List<JSONObject> sendDeleteRequestGetListJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList) {
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
    HttpDelete httpDelete = new HttpDelete(urlEndpoint);
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpDelete.setHeader(entry.getKey(), entry.getValue());
      }
    }
    try (CloseableHttpClient client = HttpClients.createDefault();) {
      HttpResponse httpResponse = client.execute(httpDelete);
      String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(),
          StandardCharsets.UTF_8);
      JSONArray data = new JSONArray(responseStr);
      for (int i = 0; i < data.length(); i++) {
        JSONObject jsonObject = data.getJSONObject(i);
        jsonObjectList.add(jsonObject);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return jsonObjectList;
  }
}