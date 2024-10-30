package com.blas.blascommon.core.service.http;

import static com.blas.blascommon.constants.SecurityConstant.AUTHORIZATION;
import static com.blas.blascommon.constants.SecurityConstant.BEARER_SPACE;
import static java.util.Collections.emptyMap;
import static org.apache.commons.lang3.StringUtils.startsWith;
import static org.apache.commons.lang3.StringUtils.substring;
import static org.springframework.util.StringUtils.hasText;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Map.Entry;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.StringUtils;

@UtilityClass
public class RequestUtils {

  public static Map<String, String> getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(AUTHORIZATION);
    if (hasText(bearerToken) && startsWith(bearerToken, BEARER_SPACE)) {
      return Map.of(AUTHORIZATION, BEARER_SPACE + substring(bearerToken, BEARER_SPACE.length()));
    }
    return emptyMap();
  }

  public static String addParameters(String hostUrl, Map<String, String> parameterList) {
    if (parameterList != null) {
      StringBuilder parameters = new StringBuilder();
      for (Entry<String, String> entry : parameterList.entrySet()) {
        parameters.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
      }
      hostUrl += "?" + StringUtils.substring(parameters.toString(), 0, parameters.length() - 1);
    }
    return hostUrl;
  }
}
