package com.blas.blascommon.utils.timeutils;

import java.time.LocalDate;

public class LunarCalendarUtils {

  private static char[] daysInGregorianMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  private static String[] monthNames = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug",
      "Sep", "Oct", "Nov", "Dec"};
  private static String[] stemNames = {"Mộc", "Mộc", "Hỏa", "Hỏa", "Thổ", "Thổ", "Kim", "Kim",
      "Thủy", "Thủy"};
  private static String[] heavenName = {"Giáp", "Ất", "Bính", "Đinh", "Mậu", "Kỷ", "Canh", "Tân",
      "Nhâm", "Quý"};
  private static String[] branchNames = {"Tý", "Sửu", "Dần", "Mão", "Thìn", "Tỵ", "Ngọ", "Mùi",
      "Thân", "Dậu", "Tuất", "Hợi"};

  public static LunarCalendar getLunarCalendar(LocalDate gregorianDate) {
    LunarCalendar lunarCalendar = new LunarCalendar();
    LunarCalendarUtils lunarCalendarUtils = new LunarCalendarUtils(lunarCalendar);
    lunarCalendarUtils.setGregorian(lunarCalendar, gregorianDate.getYear(), gregorianDate.getMonthValue(),
        gregorianDate.getDayOfMonth());
    lunarCalendarUtils.computeLunarFields(lunarCalendar);
    lunarCalendarUtils.computeSolarTerms(lunarCalendar);
    return lunarCalendar;
  }

  public LunarCalendarUtils(LunarCalendar lunarCalendar) {
    setGregorian(lunarCalendar, 1901, 1, 1);
  }

  private void setGregorian(LunarCalendar lunarCalendar, int y, int m, int d) {
    lunarCalendar.setGregorianYear(y);
    lunarCalendar.setGregorianMonth(m);
    lunarCalendar.setGregorianDate(d);
    lunarCalendar.setGregorianLeap(isGregorianLeapYear(y));
    lunarCalendar.setDayOfYear(dayOfYear(y, m, d));
    lunarCalendar.setDayOfWeek(dayOfWeek(y, m, d));
    lunarCalendar.setLunarYear(0);
    lunarCalendar.setLunarMonth(0);
    lunarCalendar.setLunarDate(0);
    lunarCalendar.setSectionalTerm(0);
    lunarCalendar.setPrincipleTerm(0);
  }

  private static boolean isGregorianLeapYear(int year) {
    boolean isLeap = false;
    if (year % 4 == 0) {
      isLeap = true;
    }
    if (year % 100 == 0) {
      isLeap = false;
    }
    if (year % 400 == 0) {
      isLeap = true;
    }
    return isLeap;
  }

  private static int daysInGregorianMonth(int y, int m) {
    int d = daysInGregorianMonth[m - 1];
    if (m == 2 && isGregorianLeapYear(y)) {
      d++; // Leap year adjustment
    }
    return d;
  }

  private static int dayOfYear(int y, int m, int d) {
    int c = 0;
    for (int i = 1; i < m; i++) { // Number of months passed
      c = c + daysInGregorianMonth(y, i);
    }
    c = c + d;
    return c;
  }

  private static int dayOfWeek(int y, int m, int d) {
    int w = 1; // 01-Jan-0001 is Monday, so base is Sunday
    y = (y - 1) % 400 + 1; // Gregorian calendar cycle is 400 years
    int ly = (y - 1) / 4; // Leap years passed
    ly = ly - (y - 1) / 100; // Adjustment
    ly = ly + (y - 1) / 400; // Adjustment
    int ry = y - 1 - ly; // Regular years passed
    w = w + ry; // Regular year has one extra week day
    w = w + 2 * ly; // Leap year has two extra week days
    w = w + dayOfYear(y, m, d);
    w = (w - 1) % 7 + 1;
    return w;
  }

  private static char[] lunarMonths = {
      //Lunar month map, 2 bytes per year, from 1900 to 2100, 402 bytes.
      //The first 4 bits represents the leap month of the year.
      //The rest 12 bits are flags indicate if the corresponding month
      //is a 29-day month. 2 bytes are stored in low-high order.
      0x00, 0x04, 0xad, 0x08, 0x5a, 0x01, 0xd5, 0x54, 0xb4, 0x09, 0x64, 0x05, 0x59, 0x45, 0x95,
      0x0a, 0xa6, 0x04, 0x55, 0x24, 0xad, 0x08, 0x5a, 0x62, 0xda, 0x04, 0xb4, 0x05, 0xb4, 0x55,
      0x52, 0x0d, 0x94, 0x0a, 0x4a, 0x2a, 0x56, 0x02, 0x6d, 0x71, 0x6d, 0x01, 0xda, 0x02, 0xd2,
      0x52, 0xa9, 0x05, 0x49, 0x0d, 0x2a, 0x45, 0x2b, 0x09, 0x56, 0x01, 0xb5, 0x20, 0x6d, 0x01,
      0x59, 0x69, 0xd4, 0x0a, 0xa8, 0x05, 0xa9, 0x56, 0xa5, 0x04, 0x2b, 0x09, 0x9e, 0x38, 0xb6,
      0x08, 0xec, 0x74, 0x6c, 0x05, 0xd4, 0x0a, 0xe4, 0x6a, 0x52, 0x05, 0x95, 0x0a, 0x5a, 0x42,
      0x5b, 0x04, 0xb6, 0x04, 0xb4, 0x22, 0x6a, 0x05, 0x52, 0x75, 0xc9, 0x0a, 0x52, 0x05, 0x35,
      0x55, 0x4d, 0x0a, 0x5a, 0x02, 0x5d, 0x31, 0xb5, 0x02, 0x6a, 0x8a, 0x68, 0x05, 0xa9, 0x0a,
      0x8a, 0x6a, 0x2a, 0x05, 0x2d, 0x09, 0xaa, 0x48, 0x5a, 0x01, 0xb5, 0x09, 0xb0, 0x39, 0x64,
      0x05, 0x25, 0x75, 0x95, 0x0a, 0x96, 0x04, 0x4d, 0x54, 0xad, 0x04, 0xda, 0x04, 0xd4, 0x44,
      0xb4, 0x05, 0x54, 0x85, 0x52, 0x0d, 0x92, 0x0a, 0x56, 0x6a, 0x56, 0x02, 0x6d, 0x02, 0x6a,
      0x41, 0xda, 0x02, 0xb2, 0xa1, 0xa9, 0x05, 0x49, 0x0d, 0x0a, 0x6d, 0x2a, 0x09, 0x56, 0x01,
      0xad, 0x50, 0x6d, 0x01, 0xd9, 0x02, 0xd1, 0x3a, 0xa8, 0x05, 0x29, 0x85, 0xa5, 0x0c, 0x2a,
      0x09, 0x96, 0x54, 0xb6, 0x08, 0x6c, 0x09, 0x64, 0x45, 0xd4, 0x0a, 0xa4, 0x05, 0x51, 0x25,
      0x95, 0x0a, 0x2a, 0x72, 0x5b, 0x04, 0xb6, 0x04, 0xac, 0x52, 0x6a, 0x05, 0xd2, 0x0a, 0xa2,
      0x4a, 0x4a, 0x05, 0x55, 0x94, 0x2d, 0x0a, 0x5a, 0x02, 0x75, 0x61, 0xb5, 0x02, 0x6a, 0x03,
      0x61, 0x45, 0xa9, 0x0a, 0x4a, 0x05, 0x25, 0x25, 0x2d, 0x09, 0x9a, 0x68, 0xda, 0x08, 0xb4,
      0x09, 0xa8, 0x59, 0x54, 0x03, 0xa5, 0x0a, 0x91, 0x3a, 0x96, 0x04, 0xad, 0xb0, 0xad, 0x04,
      0xda, 0x04, 0xf4, 0x62, 0xb4, 0x05, 0x54, 0x0b, 0x44, 0x5d, 0x52, 0x0a, 0x95, 0x04, 0x55,
      0x22, 0x6d, 0x02, 0x5a, 0x71, 0xda, 0x02, 0xaa, 0x05, 0xb2, 0x55, 0x49, 0x0b, 0x4a, 0x0a,
      0x2d, 0x39, 0x36, 0x01, 0x6d, 0x80, 0x6d, 0x01, 0xd9, 0x02, 0xe9, 0x6a, 0xa8, 0x05, 0x29,
      0x0b, 0x9a, 0x4c, 0xaa, 0x08, 0xb6, 0x08, 0xb4, 0x38, 0x6c, 0x09, 0x54, 0x75, 0xd4, 0x0a,
      0xa4, 0x05, 0x45, 0x55, 0x95, 0x0a, 0x9a, 0x04, 0x55, 0x44, 0xb5, 0x04, 0x6a, 0x82, 0x6a,
      0x05, 0xd2, 0x0a, 0x92, 0x6a, 0x4a, 0x05, 0x55, 0x0a, 0x2a, 0x4a, 0x5a, 0x02, 0xb5, 0x02,
      0xb2, 0x31, 0x69, 0x03, 0x31, 0x73, 0xa9, 0x0a, 0x4a, 0x05, 0x2d, 0x55, 0x2d, 0x09, 0x5a,
      0x01, 0xd5, 0x48, 0xb4, 0x09, 0x68, 0x89, 0x54, 0x0b, 0xa4, 0x0a, 0xa5, 0x6a, 0x95, 0x04,
      0xad, 0x08, 0x6a, 0x44, 0xda, 0x04, 0x74, 0x05, 0xb0, 0x25, 0x54, 0x03};
  // Base date: 01-Jan-1901, 4598/11/11 in Lunar calendar
  private static int baseYear = 1901;
  private static int baseMonth = 1;
  private static int baseDate = 1;
  private static int baseIndex = 0;
  private static int baseLunarYear = 4598 - 1;
  private static int baseLunarMonth = 11;
  private static int baseLunarDate = 11;

  private int computeLunarFields(LunarCalendar lunarCalendar) {
    // Gregorian year out of the computation range
    if (lunarCalendar.getGregorianYear() < 1901 || lunarCalendar.getGregorianYear() > 2100) {
      return 1;
    }
    int startYear = baseYear;
    int startMonth = baseMonth;
    int startDate = baseDate;
    lunarCalendar.setLunarYear(baseLunarYear);
    lunarCalendar.setLunarMonth(baseLunarMonth);
    lunarCalendar.setLunarDate(baseLunarDate);
    // Switching to the second base to reduce the calculation process
    // Second base date: 01-Jan-2000, 4697/11/25 in Lunar calendar
    if (lunarCalendar.getGregorianYear() >= 2000) {
      startYear = baseYear + 99;
      startMonth = 1;
      startDate = 1;
      lunarCalendar.setLunarYear(baseLunarYear + 99);
      lunarCalendar.setLunarMonth(11);
      lunarCalendar.setLunarDate(25);
    }
    // Calculating the number of days
    //    between the start date and the current date
    // The following algorithm only works
    //    for startMonth = 1 and startDate = 1
    int daysDiff = 0;
    for (int i = startYear; i < lunarCalendar.getGregorianYear(); i++) {
      daysDiff += 365;
      if (isGregorianLeapYear(i)) {
        daysDiff += 1; // leap year
      }
    }
    for (int i = startMonth; i < lunarCalendar.getGregorianMonth(); i++) {
      daysDiff += daysInGregorianMonth(lunarCalendar.getGregorianYear(), i);
    }
    daysDiff += lunarCalendar.getGregorianDate() - startDate;

    // Adding that number of days to the Lunar date
    // Then bring Lunar date into the correct range.
    // one Lunar month at a time
    lunarCalendar.setLunarDate(lunarCalendar.getLunarDate() + daysDiff);
    int lastDate = daysInLunarMonth(lunarCalendar.getLunarYear(), lunarCalendar.getLunarMonth());
    int nextMonth = nextLunarMonth(lunarCalendar.getLunarYear(), lunarCalendar.getLunarMonth());
    while (lunarCalendar.getLunarDate() > lastDate) {
      if (Math.abs(nextMonth) < Math.abs(lunarCalendar.getLunarMonth())) {
        lunarCalendar.setLunarYear(lunarCalendar.getLunarYear() + 1);
      }
      lunarCalendar.setLunarMonth(nextMonth);
      lunarCalendar.setLunarDate(lunarCalendar.getLunarDate() - lastDate);
      lastDate = daysInLunarMonth(lunarCalendar.getLunarYear(), lunarCalendar.getLunarMonth());
      nextMonth = nextLunarMonth(lunarCalendar.getLunarYear(), lunarCalendar.getLunarMonth());
    }
    return 0;
  }

  private static int[] bigLeapMonthYears = {
      // The leap months in the following years have 30 days
      6, 14, 19, 25, 33, 36, 38, 41, 44, 52, 55, 79, 117, 136, 147, 150, 155, 158, 185, 193};

  private static int daysInLunarMonth(int y, int m) {
    // Regular month: m > 0
    // Leap month: m < 0
    int index = y - baseLunarYear + baseIndex;
    int v = 0;
    int l = 0;
    int d = 30; // normal month
    if (1 <= m && m <= 8) {
      v = lunarMonths[2 * index];
      l = m - 1;
      if (((v >> l) & 0x01) == 1) {
        d = 29;
      }
    } else if (9 <= m && m <= 12) {
      v = lunarMonths[2 * index + 1];
      l = m - 9;
      if (((v >> l) & 0x01) == 1) {
        d = 29;
      }
    } else { // leap month
      v = lunarMonths[2 * index + 1];
      v = (v >> 4) & 0x0F;
      if (v != Math.abs(m)) {
        d = 0; // wrong m specified
      } else {
        d = 29;
        for (int i = 0; i < bigLeapMonthYears.length; i++) {
          if (bigLeapMonthYears[i] == index) {
            d = 30;
            break;
          }
        }
      }
    }
    return d;
  }

  private static int nextLunarMonth(int y, int m) {
    int n = Math.abs(m) + 1; // normal behavior
    if (m > 0) {
      // need to find out if we are in a leap year or not
      int index = y - baseLunarYear + baseIndex;
      int v = lunarMonths[2 * index + 1];
      v = (v >> 4) & 0x0F;
      if (v == m) {
        n = -m; // next month is a leap month
      }
    }
    if (n == 13) {
      n = 1; //roll into next year
    }
    return n;
  }

  private static char[][] sectionalTermMap = {
      {7, 6, 6, 6, 6, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 5, 5, 5, 5, 5, 4, 5, 5},   // Jan
      {5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 4, 4, 4, 4, 4, 3, 4, 4, 4, 3, 3, 4, 4, 3, 3, 3},   // Feb
      {6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5},
      // Mar
      {5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 4, 4, 5, 5, 4, 4, 4, 5, 4, 4, 4, 4, 5},
      // Apr
      {6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5, 5, 5},
      // May
      {6, 6, 7, 7, 6, 6, 6, 7, 6, 6, 6, 6, 5, 6, 6, 6, 5, 5, 6, 6, 5, 5, 5, 6, 5, 5, 5, 5, 4, 5, 5,
          5, 5},
      {7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 6, 6, 6, 7, 7},
      // Jul
      {8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7,
          7, 7},
      {8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 7},
      // Sep
      {9, 9, 9, 9, 8, 9, 9, 9, 8, 8, 9, 9, 8, 8, 8, 9, 8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 8},
      // Oct
      {8, 8, 8, 8, 7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 7},
      // Nov
      {7, 8, 8, 8, 7, 7, 8, 8, 7, 7, 7, 8, 7, 7, 7, 7, 6, 7, 7, 7, 6, 6, 7, 7, 6, 6, 6, 7, 7}
      // Dec
  };
  private static char[][] sectionalTermYear = {{13, 49, 85, 117, 149, 185, 201, 250, 250}, // Jan
      {13, 45, 81, 117, 149, 185, 201, 250, 250}, // Feb
      {13, 48, 84, 112, 148, 184, 200, 201, 250}, // Mar
      {13, 45, 76, 108, 140, 172, 200, 201, 250}, // Apr
      {13, 44, 72, 104, 132, 168, 200, 201, 250}, // May
      {5, 33, 68, 96, 124, 152, 188, 200, 201}, // Jun
      {29, 57, 85, 120, 148, 176, 200, 201, 250}, // Jul
      {13, 48, 76, 104, 132, 168, 196, 200, 201}, // Aug
      {25, 60, 88, 120, 148, 184, 200, 201, 250}, // Sep
      {16, 44, 76, 108, 144, 172, 200, 201, 250}, // Oct
      {28, 60, 92, 124, 160, 192, 200, 201, 250}, // Nov
      {17, 53, 85, 124, 156, 188, 200, 201, 250}  // Dec
  };
  private static char[][] principleTermMap = {
      {21, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 20, 20, 20, 20, 20, 19, 20,
          20, 20, 19, 19, 20},
      {20, 19, 19, 20, 20, 19, 19, 19, 19, 19, 19, 19, 19, 18, 19, 19, 19, 18, 18, 19, 19, 18, 18,
          18, 18, 18, 18, 18},
      {21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20, 20,
          20, 19, 20, 20, 20, 20},
      {20, 21, 21, 21, 20, 20, 21, 21, 20, 20, 20, 21, 20, 20, 20, 20, 19, 20, 20, 20, 19, 19, 20,
          20, 19, 19, 19, 20, 20},
      {21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21, 21, 20, 20, 21,
          21, 20, 20, 20, 21, 21},
      {22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21, 22, 21, 21, 21, 21, 20, 21, 21,
          21, 20, 20, 21, 21, 21},
      {23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22,
          23, 22, 22, 22, 22, 23},
      {23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23,
          23, 22, 22, 22, 23, 23},
      {23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23,
          23, 22, 22, 22, 23, 23},
      {24, 24, 24, 24, 23, 24, 24, 24, 23, 23, 24, 24, 23, 23, 23, 24, 23, 23, 23, 23, 22, 23, 23,
          23, 22, 22, 23, 23, 23},
      {23, 23, 23, 23, 22, 23, 23, 23, 22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22, 22,
          22, 21, 21, 22, 22, 22},
      {22, 22, 23, 23, 22, 22, 22, 23, 22, 22, 22, 22, 21, 22, 22, 22, 21, 21, 22, 22, 21, 21, 21,
          22, 21, 21, 21, 21, 22}};
  private static char[][] principleTermYear = {{13, 45, 81, 113, 149, 185, 201},     // Jan
      {21, 57, 93, 125, 161, 193, 201},     // Feb
      {21, 56, 88, 120, 152, 188, 200, 201}, // Mar
      {21, 49, 81, 116, 144, 176, 200, 201}, // Apr
      {17, 49, 77, 112, 140, 168, 200, 201}, // May
      {28, 60, 88, 116, 148, 180, 200, 201}, // Jun
      {25, 53, 84, 112, 144, 172, 200, 201}, // Jul
      {29, 57, 89, 120, 148, 180, 200, 201}, // Aug
      {17, 45, 73, 108, 140, 168, 200, 201}, // Sep
      {28, 60, 92, 124, 160, 192, 200, 201}, // Oct
      {16, 44, 80, 112, 148, 180, 200, 201}, // Nov
      {17, 53, 88, 120, 156, 188, 200, 201}  // Dec
  };

  private int computeSolarTerms(LunarCalendar lunarCalendar) {
    // Gregorian year out of the computation range
    if (lunarCalendar.getGregorianYear() < 1901 || lunarCalendar.getGregorianYear() > 2100) {
      return 1;
    }
    lunarCalendar.setSectionalTerm(sectionalTerm(lunarCalendar.getGregorianYear(),
        lunarCalendar.getGregorianMonth()));
    lunarCalendar.setPrincipleTerm(principleTerm(lunarCalendar.getGregorianYear(),
        lunarCalendar.getGregorianMonth()));
    lunarCalendar.setHeaven(heavenName[((lunarCalendar.getLunarYear() - 1) % 10)]);
    lunarCalendar.setBranchName(branchNames[((lunarCalendar.getLunarYear() - 1) % 12)]);
    return 0;
  }

  private static int sectionalTerm(int y, int m) {
    if (y < 1901 || y > 2100) {
      return 0;
    }
    int index = 0;
    int ry = y - baseYear + 1;
    while (ry >= sectionalTermYear[m - 1][index]) {
      index++;
    }
    int term = sectionalTermMap[m - 1][4 * index + ry % 4];
    return term;
  }

  private static int principleTerm(int y, int m) {
    if (y < 1901 || y > 2100) {
      return 0;
    }
    int index = 0;
    int ry = y - baseYear + 1;
    while (ry >= principleTermYear[m - 1][index]) {
      index++;
    }
    int term = principleTermMap[m - 1][4 * index + ry % 4];
    return term;
  }

  public String[] getYearTable(LunarCalendar lunarCalendar) {
    setGregorian(lunarCalendar, lunarCalendar.getGregorianYear(), 1, 1);
    computeLunarFields(lunarCalendar);
    computeSolarTerms(lunarCalendar);
    String[] table = new String[59]; // 6*9 + 5
    table[0] = getTextLine(27, "Gregorian Calendar Year: " + lunarCalendar.getGregorianYear());
    table[1] = getTextLine(27,
        "Lunar Calendar Year: " + (lunarCalendar.getLunarYear() + 1) + " (" + stemNames[
            (lunarCalendar.getLunarYear() + 1 - 1) % 10] + "-"
            + branchNames[(lunarCalendar.getLunarYear() + 1 - 1) % 12] + ")");
    int ln = 2;
    String[] mLeft = null;
    String[] mRight = null;
    for (int i = 1; i <= 6; i++) {
      table[ln] = getTextLine(0, null);
      ln++;
      mLeft = getMonthTable(lunarCalendar);
      mRight = getMonthTable(lunarCalendar);
      for (int j = 0; j < mLeft.length; j++) {
        String line = mLeft[j] + "  " + mRight[j];
        table[ln] = line;
        ln++;
      }
    }
    table[ln] = getTextLine(0, null);
    ln++;
    table[ln] = getTextLine(0,
        "##/## - Gregorian date/Lunar date," + " (*)CM## - (Leap) Lunar month first day");
    ln++;
    table[ln] = getTextLine(0, "ST## - Sectional term, PT## - Principle term");
    ln++;
    return table;
  }

  private static String getTextLine(int s, String t) {
    String str = "                                         " + "  "
        + "                                         ";
    if (t != null && s < str.length() && s + t.length() < str.length()) {
      str = str.substring(0, s) + t + str.substring(s + t.length());
    }
    return str;
  }

  private String[] getMonthTable(LunarCalendar lunarCalendar) {
    setGregorian(lunarCalendar, lunarCalendar.getGregorianYear(), lunarCalendar.getGregorianMonth(), 1);
    computeLunarFields(lunarCalendar);
    computeSolarTerms(lunarCalendar);
    String[] table = new String[8];
    String title = "                    " + monthNames[lunarCalendar.getGregorianMonth() - 1]
        + "                   ";
    String header = "  Sun   Mon   Tue   Wed   Thu   Fri   Sat ";
    String blank = "                                          ";
    table[0] = title;
    table[1] = header;
    int wk = 2;
    String line = "";
    for (int i = 1; i < lunarCalendar.getDayOfWeek(); i++) {
      line += "     " + ' ';
    }
    int days = daysInGregorianMonth(lunarCalendar.getGregorianYear(),
        lunarCalendar.getGregorianMonth());
    for (int i = lunarCalendar.getGregorianDate(); i <= days; i++) {
      line += getDateString(lunarCalendar) + ' ';
      rollUpOneDay(lunarCalendar);
      if (lunarCalendar.getDayOfWeek() == 1) {
        table[wk] = line;
        line = "";
        wk++;
      }
    }
    for (int i = lunarCalendar.getDayOfWeek(); i <= 7; i++) {
      line += "     " + ' ';
    }
    table[wk] = line;
    for (int i = wk + 1; i < table.length; i++) {
      table[i] = blank;
    }
    for (int i = 0; i < table.length; i++) {
      table[i] = table[i].substring(0, table[i].length() - 1);
    }

    return table;
  }

  private String getDateString(LunarCalendar lunarCalendar) {
    String str = "*  /  ";
    String gm = String.valueOf((lunarCalendar.getGregorianMonth() - 1 + 11) % 12 + 1);
    if (gm.length() == 1) {
      gm = ' ' + gm;
    }
    String cm = String.valueOf(Math.abs(lunarCalendar.getLunarMonth()));
    if (cm.length() == 1) {
      cm = ' ' + cm;
    }
    String gd = String.valueOf(lunarCalendar.getGregorianDate());
    if (gd.length() == 1) {
      gd = ' ' + gd;
    }
    String cd = String.valueOf(lunarCalendar.getLunarDate());
    if (cd.length() == 1) {
      cd = ' ' + cd;
    }
    if (lunarCalendar.getGregorianDate() == lunarCalendar.getSectionalTerm()) {
      str = " ST" + gm;
    } else if (lunarCalendar.getGregorianDate() == lunarCalendar.getPrincipleTerm()) {
      str = " PT" + gm;
    } else if (lunarCalendar.getLunarDate() == 1 && lunarCalendar.getLunarMonth() > 0) {
      str = " CM" + cm;
    } else if (lunarCalendar.getLunarDate() == 1 && lunarCalendar.getLunarMonth() < 0) {
      str = "*CM" + cm;
    } else {
      str = gd + '/' + cd;
    }
    return str;
  }

  private void rollUpOneDay(LunarCalendar lunarCalendar) {
    lunarCalendar.setDayOfWeek(lunarCalendar.getDayOfWeek() % 7 + 1);
    lunarCalendar.setDayOfYear(lunarCalendar.getDayOfYear() + 1);
    lunarCalendar.setGregorianDate(lunarCalendar.getGregorianDate() + 1);
    int days = daysInGregorianMonth(lunarCalendar.getGregorianYear(),
        lunarCalendar.getGregorianMonth());
    if (lunarCalendar.getGregorianDate() > days) {
      lunarCalendar.setGregorianDate(1);
      lunarCalendar.setGregorianMonth(lunarCalendar.getGregorianMonth() + 1);
      if (lunarCalendar.getGregorianMonth() > 12) {
        lunarCalendar.setGregorianMonth(1);
        lunarCalendar.setGregorianYear(lunarCalendar.getGregorianYear() + 1);
        lunarCalendar.setDayOfYear(1);
        lunarCalendar.setGregorianLeap(isGregorianLeapYear(lunarCalendar.getGregorianYear()));
      }
      lunarCalendar.setSectionalTerm(
          sectionalTerm(lunarCalendar.getGregorianYear(), lunarCalendar.getGregorianMonth()));
      lunarCalendar.setPrincipleTerm(
          sectionalTerm(lunarCalendar.getGregorianYear(), lunarCalendar.getGregorianMonth()));
    }
    lunarCalendar.setLunarDate(lunarCalendar.getLunarDate() + 1);
    days = daysInLunarMonth(lunarCalendar.getLunarYear(), lunarCalendar.getLunarMonth());
    if (lunarCalendar.getLunarDate() > days) {
      lunarCalendar.setLunarDate(1);
      lunarCalendar.setLunarMonth(
          nextLunarMonth(lunarCalendar.getLunarYear(), lunarCalendar.getLunarMonth()));
      if (lunarCalendar.getLunarMonth() == 1) {
        lunarCalendar.setLunarYear(lunarCalendar.getLunarYear() + 1);
      }
    }
  }
}