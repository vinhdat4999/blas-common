package com.blas.blascommon.utils.httprequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;

public class DeleteRequest {

    public static String sendDeleteRequestGetStringResponse(String hostUrl,
            Map<String, String> parameterList,
            Map<String, String> headerList) {
        String urlEndpoint = hostUrl;
        StringBuilder sb;
        if (parameterList != null) {
            sb = new StringBuilder("");
            for (String key : parameterList.keySet()) {
                sb.append(key).append("=").append(parameterList.get(key)).append("&");
            }
            urlEndpoint += "?" + sb.toString().substring(0, sb.toString().length() - 1);
        }
        String response = null;
        try {
            HttpDelete httpDelete = new HttpDelete(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpDelete.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(httpDelete);
            response = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static JSONArray sendDeleteRequestGetJsonArrayResponse(String hostUrl,
            Map<String, String> parameterList,
            Map<String, String> headerList) {
        String urlEndpoint = hostUrl;
        StringBuilder sb;
        if (parameterList != null) {
            sb = new StringBuilder("");
            for (String key : parameterList.keySet()) {
                sb.append(key).append("=").append(parameterList.get(key)).append("&");
            }
            urlEndpoint += "?" + sb.toString().substring(0, sb.toString().length() - 1);
        }
        JSONArray response = null;
        try {
            HttpDelete httpDelete = new HttpDelete(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpDelete.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(httpDelete);
            String responseStr = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
            response = new JSONArray(responseStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static List<JSONObject> sendDeleteRequestGetListJsonObjectResponse(String hostUrl,
            Map<String, String> parameterList,
            Map<String, String> headerList) {
        String urlEndpoint = hostUrl;
        StringBuilder sb;
        if (parameterList != null) {
            sb = new StringBuilder("");
            for (String key : parameterList.keySet()) {
                sb.append(key).append("=").append(parameterList.get(key)).append("&");
            }
            urlEndpoint += "?" + sb.toString().substring(0, sb.toString().length() - 1);
        }
        List<JSONObject> jsonObjectList = new ArrayList<>();
        try {
            HttpDelete httpDelete = new HttpDelete(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpDelete.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(httpDelete);
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

//    public static int sendDeleteRequestToLelApi(String id) {
//        int responseId = 0;
//        try {
//            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
//            HttpDelete request = new HttpDelete(Constants.LEL_DELETE_REQUEST_URL + "?id=" + id);
//            CloseableHttpResponse response = httpClient.execute(request);
//            String jsonStr = EntityUtils.toString(response.getEntity());
//            responseId = Integer.parseInt(jsonStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return responseId;
//    }
}
