package com.blas.blascommon.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidUtils {

  public static boolean isValidEmail(String email) {
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    return isValidPattern(email, regex);
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    String regex = "^(?:0|84|\\+84)[035789]\\d{8}\\b";
    return isValidPattern(phoneNumber, regex);
  }

  private boolean isValidPattern(String input, String regex) {
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(input);
    return matcher.matches();
  }
}
