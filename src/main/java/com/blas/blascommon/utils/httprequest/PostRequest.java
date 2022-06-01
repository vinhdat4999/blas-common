package com.blas.blascommon.utils.httprequest;

import java.util.Map;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class PostRequest {

    public static String sendPostRequestWithJsonObjectPayloadGetStringResponse(String hostUrl,
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
            HttpPost httpPost = new HttpPost(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpPost.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            StringEntity entity = new StringEntity(payload.toString(),
                    ContentType.APPLICATION_JSON);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response2 = httpClient.execute(httpPost);
            response = IOUtils.toString(response2.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static JSONArray sendPostRequestWithJsonObjectPayloadGetJsonArrayResponse(String hostUrl,
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
            HttpPost httpPost = new HttpPost(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpPost.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            StringEntity entity = new StringEntity(payload.toString(),
                    ContentType.APPLICATION_JSON);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response2 = httpClient.execute(httpPost);
            response = new JSONArray(IOUtils.toString(response2.getEntity().getContent(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static String sendPostRequestWithJsonArrayPayloadGetStringResponse(String hostUrl,
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
            HttpPost httpPost = new HttpPost(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpPost.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            StringEntity entity = new StringEntity(payload.toString(),
                    ContentType.APPLICATION_JSON);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response2 = httpClient.execute(httpPost);
            response = IOUtils.toString(response2.getEntity().getContent(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }

    public static JSONArray sendPostRequestWithJsonArrayPayloadGetJsonArrayResponse(String hostUrl,
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
            HttpPost httpPost = new HttpPost(urlEndpoint);
            if (headerList != null) {
                for (String headerKey : headerList.keySet()) {
                    httpPost.setHeader(headerKey, headerList.get(headerKey));
                }
            }
            StringEntity entity = new StringEntity(payload.toString(),
                    ContentType.APPLICATION_JSON);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            httpPost.setEntity(entity);
            CloseableHttpResponse response2 = httpClient.execute(httpPost);
            response = new JSONArray(IOUtils.toString(response2.getEntity().getContent(), "UTF-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}
