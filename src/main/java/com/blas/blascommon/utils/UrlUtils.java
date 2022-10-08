package com.blas.blascommon.utils;

import java.util.Arrays;

public class UrlUtils {

  private UrlUtils() {
  }

  private static final char[] SOURCE_CHARACTERS = {};

  private static final char[] DESTINATION_CHARACTERS = {'A', 'A', 'A', 'A', 'E', 'E', 'E', 'I',
      'I', 'O', 'O', 'O',
      'O', 'U', 'U', 'Y', 'a', 'a', 'a', 'a', 'e', 'e', 'e', 'i', 'i', 'o', 'o', 'o', 'o',
      'u', 'u', 'y', 'A',
      'a', 'D', 'd', 'I', 'i', 'U', 'u', 'O', 'o', 'U', 'u', 'A', 'a', 'A', 'a', 'A', 'a',
      'A', 'a', 'A', 'a',
      'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'A', 'a', 'E', 'e', 'E',
      'e', 'E', 'e', 'E',
      'e', 'E', 'e', 'E', 'e', 'E', 'e', 'E', 'e', 'I', 'i', 'I', 'i', 'O', 'o', 'O', 'o',
      'O', 'o', 'O', 'o',
      'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'O', 'o', 'U',
      'u', 'U', 'u', 'U',
      'u', 'U', 'u', 'U', 'u', 'U', 'u', 'U', 'u',};

  private static char removeAccent(char ch) {
    int index = Arrays.binarySearch(SOURCE_CHARACTERS, ch);
    if (index >= 0) {
      ch = DESTINATION_CHARACTERS[index];
    }
    return ch;
  }

  public static String toUrlStandard(String str) {
    StringBuilder sb = new StringBuilder(str);
    sb = new StringBuilder(sb.toString().replace(" ", "-"));
    sb = new StringBuilder(sb.toString().replace("'", ""));
    sb = new StringBuilder(sb.toString().replace(",", ""));
    sb = new StringBuilder(sb.toString().replace(".", ""));
    sb = new StringBuilder(sb.toString().replace("?", ""));
    sb = new StringBuilder(sb.toString().replace("/", ""));
    sb = new StringBuilder(sb.toString().replace("\\", ""));
    sb = new StringBuilder(sb.toString().replace("|", ""));
    sb = new StringBuilder(sb.toString().replace("@", ""));
    sb = new StringBuilder(sb.toString().replace("#", ""));
    sb = new StringBuilder(sb.toString().replace("$", ""));
    sb = new StringBuilder(sb.toString().replace("%", ""));
    sb = new StringBuilder(sb.toString().replace("^", ""));
    sb = new StringBuilder(sb.toString().replace("&", ""));
    sb = new StringBuilder(sb.toString().replace("*", ""));
    sb = new StringBuilder(sb.toString().replace("(", ""));
    sb = new StringBuilder(sb.toString().replace(")", ""));
    sb = new StringBuilder(sb.toString().replace("_", ""));
    sb = new StringBuilder(sb.toString().replace("=", ""));
    sb = new StringBuilder(sb.toString().replace("+", ""));
    sb = new StringBuilder(sb.toString().replace("~", ""));
    sb = new StringBuilder(sb.toString().replace("!", ""));
    sb = new StringBuilder(sb.toString().replace(";", ""));
    sb = new StringBuilder(sb.toString().replace("[", ""));
    sb = new StringBuilder(sb.toString().replace("]", ""));
    sb = new StringBuilder(sb.toString().replace("{", ""));
    sb = new StringBuilder(sb.toString().replace("}", ""));
    sb = new StringBuilder(sb.toString().replace("\"", ""));
    sb = new StringBuilder(sb.toString().replace(":", ""));
    sb = new StringBuilder(sb.toString().toLowerCase());
    for (int i = 0; i < sb.length(); i++) {
      sb.setCharAt(i, removeAccent(sb.charAt(i)));
    }
    return sb.toString();
  }
}