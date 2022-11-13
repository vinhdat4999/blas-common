package com.blas.blascommon.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

  public static final String DASH = "-";
  public static final String SLASH = "/";
  public static final String COMMA = ",";
  public static final String DOT = ".";
  public static final String EMPTY = "";
  public static final String SPACE = " ";
  public static final String UNDERSCORE = "_";

  public static boolean isBlank(String s) {
    return s == null || s.trim().length() == 0;
  }

  public static String safeTrim(String s) {
    return s == null ? "" : s.trim();
  }
}
