package com.blas.blascommon.utils;

import static org.apache.commons.lang3.StringUtils.equalsAny;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IpUtils {

  private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";

  public static String getIpAddress(HttpServletRequest request) {
    try {
      return (request.getHeader(X_FORWARDED_FOR) == null) ? request.getRemoteAddr()
          : request.getHeader(X_FORWARDED_FOR);
    } catch (Exception exception) {
      return "Invalid IP: " + exception.getMessage();
    }
  }

  public static boolean isLocalRequest(HttpServletRequest request) {
    return equalsAny(request.getRemoteAddr(), "127.0.0.1", "0:0:0:0:0:0:0:1");
  }
}
