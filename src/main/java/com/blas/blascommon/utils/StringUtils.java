package com.blas.blascommon.utils;

public class StringUtils {

  private StringUtils() {
  }

  public static boolean isBlank(String s) {
    return s == null || s.trim().length() == 0;
  }

  public static String safeTrim(String s) {
    return s == null ? "" : s.trim();
  }
}