package com.blas.blascommon.interceptors;

import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.equalsAny;

import com.blas.blascommon.utils.ValidUtils;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityCheckUtils {

  boolean isLocalRequest(HttpServletRequest request) {
    return equalsAny(request.getRemoteAddr(), "127.0.0.1", "0:0:0:0:0:0:0:1");
  }

  String getRequestBody(HttpServletRequest request) throws IOException {
    return request.getReader().lines().collect(joining());
  }

  boolean isPotentialRiskRequest(HttpServletRequest request) throws IOException {
    return isPotentialSqlInjection(request);
  }

  boolean isPotentialSqlInjection(HttpServletRequest request) throws IOException {
    Pattern sqlKeywordPattern = Pattern.compile(
        "(?i)\\b(union|select|insert|update|delete|drop|truncate|alter|exec|execute)\\b");
    return ValidUtils.anyMatch(sqlKeywordPattern, request.getRequestURL().toString(),
        request.getQueryString(), getRequestBody(request));
  }
}
