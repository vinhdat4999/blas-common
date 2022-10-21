package com.blas.blascommon.utils.timeutils;

import lombok.Data;

@Data
public class LunarCalendar {

  private int gregorianYear;
  private int gregorianMonth;
  private int gregorianDate;
  private boolean isGregorianLeap;
  private int dayOfYear;
  private int dayOfWeek;
  private int lunarYear;
  private int lunarMonth;
  private int lunarDate;
  private int sectionalTerm;
  private int principleTerm;
  private String heaven;
  private String branchName;
}