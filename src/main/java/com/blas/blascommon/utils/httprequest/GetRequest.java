package com.blas.blascommon.utils.httprequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class GetRequest {

  public static String sendGetRequestGetStringResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList) {
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
      HttpGet httpGet = new HttpGet(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpGet.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      HttpClient client = HttpClients.createDefault();
      HttpResponse httpResponse = client.execute(httpGet);
      response = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONObject sendGetRequestGetJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList) {
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
      HttpGet httpGet = new HttpGet(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpGet.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      HttpClient client = HttpClients.createDefault();
      HttpResponse httpResponse = client.execute(httpGet);
      String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
      response = new JSONObject(responseStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static JSONArray sendGetRequestGetJsonArrayResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList) {
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
      HttpGet httpGet = new HttpGet(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpGet.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      HttpClient client = HttpClients.createDefault();
      HttpResponse httpResponse = client.execute(httpGet);
      String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
      response = new JSONArray(responseStr);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }

  public static List<JSONObject> sendGetRequestGetListJsonObjectResponse(String hostUrl,
      Map<String, String> parameterList,
      Map<String, String> headerList) {
    String urlEndpoint = hostUrl;
    StringBuilder sb;
    if (parameterList != null) {
      sb = new StringBuilder("");
      for (String key : parameterList.keySet()) {
        sb.append(key).append("=").append(parameterList.get(key)).append("&");
      }
      urlEndpoint += "?" + sb.substring(0, sb.toString().length() - 1);
    }
    List<JSONObject> jsonObjectList = new ArrayList<>();
    try {
      HttpGet httpGet = new HttpGet(urlEndpoint);
      if (headerList != null) {
        for (String headerKey : headerList.keySet()) {
          httpGet.setHeader(headerKey, headerList.get(headerKey));
        }
      }
      HttpClient client = HttpClients.createDefault();
      HttpResponse httpResponse = client.execute(httpGet);
      String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
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