package com.blas.blascommon.utils.datetimeutils;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DateTimeUtilsTest {

  @Test
  void testGetTimeLabelNow() {
    LocalDateTime now = LocalDateTime.now();
    String timeLabel = DateTimeUtils.getTimeLabel(now);
    Assertions.assertEquals("now", timeLabel);
  }

  @Test
  void testGetTimeLabelMinutesAgo() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime minutesAgo = now.minusMinutes(5);
    String timeLabel = DateTimeUtils.getTimeLabel(minutesAgo);
    Assertions.assertEquals("5 minute ago", timeLabel);
  }

  @Test
  void testGetTimeLabelHoursAgo() {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime hoursAgo = now.minusHours(2);
    String timeLabel = DateTimeUtils.getTimeLabel(hoursAgo);
    Assertions.assertEquals("2 hour ago", timeLabel);
  }

  @Test
  void testGetTimeLabelPastDate() {
    LocalDateTime pastDateTime = LocalDateTime.of(2021, 10, 1, 10, 30);
    String timeLabel = DateTimeUtils.getTimeLabel(pastDateTime);
    Assertions.assertEquals("01/10/2021 10:30", timeLabel);
  }

  @Test
  void testIsValidLocalDateValid() {
    String validDate = "2022-01-01";
    boolean isValid = DateTimeUtils.isValidLocalDate(validDate);
    Assertions.assertTrue(isValid);
  }

  @Test
  void testIsValidLocalDateInvalid() {
    String invalidDate = "2022-13-01";
    boolean isValid = DateTimeUtils.isValidLocalDate(invalidDate);
    Assertions.assertFalse(isValid);
  }

  @Test
  void testIsValidDateValid() {
    int year = 2022;
    int month = 1;
    int day = 1;
    boolean isValid = DateTimeUtils.isValidDate(year, month, day);
    Assertions.assertTrue(isValid);
  }

  @Test
  void testIsValidDateInvalid() {
    int year = 2022;
    int month = 13;
    int day = 1;
    boolean isValid = DateTimeUtils.isValidDate(year, month, day);
    Assertions.assertFalse(isValid);
  }

  @Test
  void testConvertLocalDateTimeToTimeFormatValidFormat() {
    LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 10, 30);
    String format = "dd/MM/yyyy HH:mm:ss";
    String formattedTime = DateTimeUtils.convertLocalDateTimeToTimeFormat(dateTime, format);
    Assertions.assertEquals("01/01/2022 10:30:00", formattedTime);
  }

  @Test
  void testConvertLocalDateTimeToTimeFormatInvalidFormat() {
    LocalDateTime dateTime = LocalDateTime.of(2022, 1, 1, 10, 30);
    String invalidFormat = "invalid";
    String formattedTime = DateTimeUtils.convertLocalDateTimeToTimeFormat(dateTime, invalidFormat);
    Assertions.assertEquals("", formattedTime);
  }
}
