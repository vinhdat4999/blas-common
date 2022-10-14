package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.StatisticLog;
import java.time.LocalDate;

public interface StatisticLogService {

  public StatisticLog getStatisticLogByStatisticLogId(String statisticLogId);

  public int getNumOfReportInDateOfUserId(String userId, LocalDate date);

  public StatisticLog createStatisticLog(StatisticLog statisticLog);

  public void updateStatisticLog(StatisticLog statisticLog);
}