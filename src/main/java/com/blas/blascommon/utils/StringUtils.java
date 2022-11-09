package com.blas.blascommon.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

  public static boolean isBlank(String s) {
    return s == null || s.trim().length() == 0;
  }

  public static String safeTrim(String s) {
    return s == null ? "" : s.trim();
  }
}
