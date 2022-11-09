package com.blas.blascommon.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ValidUtils {

  public static boolean isValidEmail(String email) {
    String regex = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(email);
    return matcher.matches();
  }

  public static boolean isValidPhoneNumber(String phoneNumber) {
    String regex = "(84|0[3|5|7|8|9])+([0-9]{8})\\b";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(phoneNumber);
    return matcher.matches();
  }

  public static boolean isValidDate(int year, int month, int day) {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
    simpleDateFormat.setLenient(false);
    try {
      simpleDateFormat.parse(
          new StringBuilder().append(year).append("/").append(month).append("/").append(day)
              .toString());
    } catch (ParseException e) {
      return false;
    }
    return true;
  }
}
