package com.blas.blascommon.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReportType {
  MARKET_INFO("MARKET INFO"),
  SPENDING_STATISTIC("SPENDING_STATISTIC");

  private final String type;
}
