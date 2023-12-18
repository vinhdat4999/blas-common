package com.blas.blascommon.utils;

import java.util.Optional;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StringUtils {

  public static final String PLUS = "+";
  public static final String HYPHEN = "-";
  public static final String ASTERISK = "*";
  public static final String SLASH = "/";
  public static final String COLON = ":";
  public static final String PERCENT = "%";
  public static final String COMMA = ",";
  public static final String DOT = ".";
  public static final String EMPTY = "";
  public static final String SPACE = " ";
  public static final String EQUAL = "=";
  public static final String QUESTION_MARK = "?";
  public static final String AMPERSAND = "&";
  public static final String UNDERSCORE = "_";

  public static String safeTrim(String text) {
    return Optional.ofNullable(text)
        .map(String::trim)
        .orElse(EMPTY);
  }
}
