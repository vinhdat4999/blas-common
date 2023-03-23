package com.blas.blascommon.utils;

import java.security.SecureRandom;
import java.util.UUID;
import lombok.experimental.UtilityClass;

@UtilityClass
public class IdUtils {

  private static SecureRandom random = new SecureRandom();

  public static String genUUID() {
    return UUID.randomUUID().toString();
  }

  public static String genMixID() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    int targetStringLength = 6;
    String generatedString = "";
    generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return generatedString.toUpperCase();
  }

  public static String genMixID(int lengthOfId) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 122; // letter 'z'
    String generatedString = "";
    generatedString = random.ints(leftLimit, rightLimit + 1)
        .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97)).limit(lengthOfId)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return generatedString.toUpperCase();
  }

  public static String genNumericID() {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 57; // numeral '9'
    int targetStringLength = 6;
    String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57))
        .limit(targetStringLength)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return generatedString.toUpperCase();
  }

  public static String genNumericID(int lengthOfId) {
    int leftLimit = 48; // numeral '0'
    int rightLimit = 57; // numeral '9'
    String generatedString = random.ints(leftLimit, rightLimit + 1).filter(i -> (i <= 57))
        .limit(lengthOfId)
        .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
        .toString();
    return generatedString.toUpperCase();
  }
}
