package com.blas.blascommon.utils;

import com.google.gson.Gson;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
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

public class HttpRequest {

    public static int sendPostRequestToImgbbApi(byte[] fileContent, String fileName, int expiration) {
        int responseId = 0;
        try {
            String urlRequest = Constants.IMGBB_URL + "?key=" + Constants.IMGBB_API_KEY;
            if (!fileName.equals("")) {
                urlRequest += "&name=" + fileName;
            }
            if (expiration != 0) {
                urlRequest += "&expiration=" + expiration;
            }
            HttpPost post = new HttpPost(urlRequest);
            List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
            urlParameters.add(
                    new BasicNameValuePair("image", Base64.encodeBase64String(fileContent)));
            post.setEntity(new UrlEncodedFormEntity(urlParameters));
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse res = httpClient.execute(post);
            String jsonStr = EntityUtils.toString(res.getEntity());
            JSONObject data = new JSONObject(jsonStr);
            JSONObject displayUrl = data.getJSONObject("data");
            responseId = sendPostRequestToLelApi(displayUrl.getString("url"), fileContent.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseId;
    }

    public static List<UrlBase> sendGetRequestToLelApi() {
        List<UrlBase> urlList = new ArrayList<UrlBase>();
        try {
            HttpGet httpGet = new HttpGet(Constants.LEL_GET_REQUEST_URL);
            HttpClient client = HttpClients.createDefault();
            HttpResponse httpResponse = client.execute(httpGet);
            String content = IOUtils.toString(httpResponse.getEntity().getContent(), "UTF-8");
            JSONArray data = new JSONArray(content);
            for (int i = 0; i < data.length(); i++) {
                Gson g = new Gson();
                JSONObject ff = data.getJSONObject(i);
                UrlBase temp = g.fromJson(ff.toString(), UrlBase.class);
                urlList.add(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urlList;
    }

    private static int sendPostRequestToLelApi(String url, int size) {
        int responseId = 0;
        try {
            String payload =
                    "{" + "\"Date\": \"" + LocalDate.now().toString() + "\", " + "\"Size\": \""
                            + size + "\", "
                            + "\"Url\": \"" + url + "\"" + "}";
            StringEntity entity = new StringEntity(payload, ContentType.APPLICATION_JSON);
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost request = new HttpPost(Constants.LEL_POST_REQUEST_URL);
            request.setEntity(entity);
            CloseableHttpResponse response = httpClient.execute(request);
            String jsonStr = EntityUtils.toString(response.getEntity());
            responseId = Integer.parseInt(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseId;
    }

    public static int sendDeleteRequestToLelApi(String id) {
        int responseId = 0;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete request = new HttpDelete(Constants.LEL_DELETE_REQUEST_URL + "?id=" + id);
            CloseableHttpResponse response = httpClient.execute(request);
            String jsonStr = EntityUtils.toString(response.getEntity());
            responseId = Integer.parseInt(jsonStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseId;
    }

}
