package com.blas.blascommon.utils;

import static com.blas.blascommon.utils.StringUtils.SLASH;
import static com.blas.blascommon.utils.timeutils.TimeUtils.DATE_SLASH_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_SLASH_FORMAT);
    simpleDateFormat.setLenient(false);
    try {
      simpleDateFormat.parse(
          new StringBuilder().append(year).append(SLASH).append(month).append(SLASH).append(day)
              .toString());
    } catch (ParseException e) {
      return false;
    }
    return true;
  }
}
