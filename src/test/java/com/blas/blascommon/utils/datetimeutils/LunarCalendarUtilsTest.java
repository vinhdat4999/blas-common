package com.blas.blascommon.utils.datetimeutils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class LunarCalendarUtilsTest {

  @Test
  void shouldReturnLunarCalendarForValidGregorianDate() {
    LocalDate gregorianDate = LocalDate.of(2022, 12, 1);
    LunarCalendar result = LunarCalendarUtils.getLunarCalendar(gregorianDate);
    assertNotNull(result);
    assertEquals(2022, result.getGregorianYear());
    assertEquals(12, result.getGregorianMonth());
    assertEquals(1, result.getGregorianDate());
  }

  @Test
  void shouldHandleLeapYearInGregorianDate() {
    LocalDate gregorianDate = LocalDate.of(2020, 2, 29);
    LunarCalendar result = LunarCalendarUtils.getLunarCalendar(gregorianDate);
    assertNotNull(result);
    assertTrue(result.isGregorianLeap());
  }

  @Test
  void shouldHandleNonLeapYearInGregorianDate() {
    LocalDate gregorianDate = LocalDate.of(2021, 2, 28);
    LunarCalendar result = LunarCalendarUtils.getLunarCalendar(gregorianDate);
    assertNotNull(result);
    assertFalse(result.isGregorianLeap());
  }
}
