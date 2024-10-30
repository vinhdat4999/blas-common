package com.blas.blascommon.core.service.http;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;

@Getter
@RequiredArgsConstructor
public enum HttpMethod {

  GET(HttpGet.class),
  POST(HttpPost.class),
  PUT(HttpPut.class),
  DELETE(HttpDelete.class);

  private final Class<? extends HttpRequestBase> clazz;
}
