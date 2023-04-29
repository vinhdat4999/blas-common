package com.blas.blascommon.utils;

import static com.blas.blascommon.utils.StringUtils.SLASH;
import static com.blas.blascommon.utils.datetimeutils.DateTimeUtils.DATE_YYYYMMDD_SLASH_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidUtils {

  public static boolean isValidEmail(String email) {
    String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    String regex = "^(?:0|84)[035789]\\d{8}\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
  }

  public static boolean isValidDate(int year, int month, int day) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_YYYYMMDD_SLASH_FORMAT);
    simpleDateFormat.setLenient(false);
    try {
      simpleDateFormat.parse(year + SLASH + month + SLASH + day);
    } catch (ParseException e) {
      return false;
    }
    return true;
  }

  public static boolean isAllDigitCharacter(String input) {
    return input.chars().allMatch(Character::isDigit);
  }

  public boolean anyMatch(Pattern pattern, String... strings) {
    return Arrays.stream(strings).anyMatch(pattern.asPredicate());
  }

  public boolean allMatch(Pattern pattern, String... strings) {
    return Arrays.stream(strings).allMatch(pattern.asPredicate());
  }
}
