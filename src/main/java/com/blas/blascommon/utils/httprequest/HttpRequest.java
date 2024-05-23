package com.blas.blascommon.utils.httprequest;

import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static com.blas.blascommon.utils.httprequest.RequestUtils.addParameters;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.http.entity.ContentType.APPLICATION_JSON;

import com.blas.blascommon.payload.HttpResponse;
import com.blas.blascommon.properties.BlasRequestConfigProperties;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class HttpRequest {

  private static final String METHOD_CANNOT_BE_NULL = "Method cannot be null";
  private static final int DEFAULT_TIMEOUT = 30000;

  @Lazy
  private final BlasRequestConfigProperties blasRequestConfigProperties;

  public HttpResponse sendPostRequestWithFormUrlEncodedPayload(String hostUrl,
      Map<String, String> parameterList, Map<String, String> headerList,
      Map<String, String> payload) throws IOException {
    log.debug("Start send POST request...");
    log.debug("Connecting to {}...", hostUrl);
    try (CloseableHttpClient client = HttpClients.createDefault()) {
      HttpPost httpPost = new HttpPost(addParameters(hostUrl, parameterList));
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
      CloseableHttpResponse response = client.execute(httpPost);
      int statusCode = response.getStatusLine().getStatusCode();
      log.debug("Received POST response. HTTP status: {}", statusCode);
      return HttpResponse.builder().statusCode(statusCode)
          .response(EntityUtils.toString(response.getEntity())).build();
    }
  }

  public HttpResponse sendRequestWithoutRequestBody(String hostUrl, HttpMethod method,
      Map<String, String> parameterList, Map<String, String> headerList)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return sendRequestGetStringResponse(hostUrl, method, parameterList, headerList, null);
  }

  public HttpResponse sendRequestWithJsonObjectPayload(String hostUrl, HttpMethod method,
      Map<String, String> parameterList, Map<String, String> headerList, JSONObject payload)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return sendRequestGetStringResponse(hostUrl, method, parameterList, headerList,
        payload.toString());
  }

  public HttpResponse sendRequestWithStringPayload(String hostUrl, HttpMethod method,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return sendRequestGetStringResponse(hostUrl, method, parameterList, headerList, payload);
  }

  public HttpResponse sendRequestWithJsonArrayPayload(String hostUrl, HttpMethod method,
      Map<String, String> parameterList, Map<String, String> headerList, JSONArray payload)
      throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
    return sendRequestGetStringResponse(hostUrl, method, parameterList, headerList,
        payload.toString());
  }

  private HttpResponse sendRequestGetStringResponse(String hostUrl, HttpMethod method,
      Map<String, String> parameterList, Map<String, String> headerList, String payload)
      throws IOException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
    if (method == null) {
      throw new IllegalArgumentException(METHOD_CANNOT_BE_NULL);
    }
    log.debug("Start send {} request...", method);
    log.debug("Connecting to {}...", hostUrl);
    HttpRequestBase httpMethod = method.getClazz().getDeclaredConstructor(String.class)
        .newInstance(addParameters(hostUrl, parameterList));
    if (headerList != null) {
      for (Entry<String, String> entry : headerList.entrySet()) {
        httpMethod.setHeader(entry.getKey(), entry.getValue());
      }
    }
    StringEntity entity = new StringEntity(Optional.ofNullable(payload).orElse(EMPTY),
        APPLICATION_JSON);
    int httpRequestTimeout = blasRequestConfigProperties.getHttpRequestTimeout();
    if (httpRequestTimeout == 0) {
      httpRequestTimeout = DEFAULT_TIMEOUT;
      log.debug("HTTP timeout not set. Using default timeout: {}", DEFAULT_TIMEOUT);
    } else {
      log.debug("HTTP timeout = {}", httpRequestTimeout);
    }
    CloseableHttpClient httpClient = HttpClients.custom()
        .setRedirectStrategy(new LaxRedirectStrategy()).setDefaultRequestConfig(
            RequestConfig.custom()
                .setConnectTimeout(httpRequestTimeout)
                .setConnectionRequestTimeout(httpRequestTimeout)
                .setSocketTimeout(httpRequestTimeout)
                .build())
        .build();
    if (httpMethod instanceof HttpEntityEnclosingRequestBase httpentityenclosingrequestbase) {
      httpentityenclosingrequestbase.setEntity(entity);
    }
    CloseableHttpResponse response = httpClient.execute(httpMethod);
    int statusCode = response.getStatusLine().getStatusCode();
    log.debug("Received {} response. HTTP status: {}", method, statusCode);
    return HttpResponse.builder()
        .statusCode(statusCode)
        .response(IOUtils.toString(response.getEntity().getContent(), UTF_8))
        .build();
  }
}
