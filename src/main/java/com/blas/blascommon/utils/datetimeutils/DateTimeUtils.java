package com.blas.blascommon.utils.datetimeutils;

import static com.blas.blascommon.utils.StringUtils.HYPHEN;
import static com.blas.blascommon.utils.StringUtils.SLASH;
import static com.blas.blascommon.utils.StringUtils.SPACE;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateTimeUtils {

  public static final String STANDARD_DATE_TIME_FORMAT_1 = "dd/MM/yyyy HH:mm:ss";
  public static final String STANDARD_DATE_TIME_FORMAT_2 = "HH:mm:ss dd/MM/yyyy";
  public static final String DATE_YYYYMMDD_HYPHEN_FORMAT = "yyyy-MM-dd";
  public static final String DATE_DDMMYYYY_HYPHEN_FORMAT = "dd-MM-yyyy";
  public static final String DATE_YYYYMMDD_SLASH_FORMAT = "yyyy/MM/dd";
  public static final String DATE_DDMMYYYY_SLASH_FORMAT = "dd/MM/yyyy";

  public static String getTimeLabel(LocalDateTime datetime) {
    LocalDateTime now = LocalDateTime.now();
    if (now.minusSeconds(60).isBefore(datetime)) {
      return "now";
    }
    if (now.minusMinutes(60).isBefore(datetime)) {
      return (now.getMinute() - datetime.getMinute()) + " minute ago";
    }
    if (now.minusHours(24).isBefore(datetime)) {
      return (now.getHour() - datetime.getHour()) + " hour ago";
    }
    String[] temp = datetime.toString().split("T");
    String[] temp2 = temp[0].split(HYPHEN);
    return temp2[2] + SLASH + temp2[1] + SLASH + temp2[0] + SPACE + temp[1];
  }

  public static boolean isValidLocalDate(String dateStr) {
    try {
      LocalDate.parse(dateStr);
      return true;
    } catch (DateTimeParseException e) {
      return false;
    }
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
}
