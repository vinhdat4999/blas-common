package com.blas.blascommon.utils;

import java.time.LocalDateTime;

public class TimeUtils {

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
    String[] temp2 = temp[0].split("-");
    return temp2[2] + "/" + temp2[1] + "/" + temp2[0] + " " + temp[1];
  }

  public static LocalDateTime getTimeNow() {
    return LocalDateTime.now();
  }
}