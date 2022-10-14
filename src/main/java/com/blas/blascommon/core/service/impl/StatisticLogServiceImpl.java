package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.STATISTIC_LOG_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.StatisticLogDao;
import com.blas.blascommon.core.model.StatisticLog;
import com.blas.blascommon.core.service.StatisticLogService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class StatisticLogServiceImpl implements StatisticLogService {

  @Autowired
  private StatisticLogDao statisticLogDao;

  @Override
  public StatisticLog getStatisticLogByStatisticLogId(String statisticLogId) {
    Optional<StatisticLog> statisticLog = statisticLogDao.findById(statisticLogId);
    if (statisticLog.isEmpty()) {
      throw new NotFoundException(STATISTIC_LOG_ID_NOT_FOUND);
    }
    return statisticLog.get();
  }

  @Override
  public int getNumOfReportInDateOfUserId(String userId, LocalDate date) {
    return statisticLogDao.getNumOfReportInDateOfUserId(userId, date);
  }

  @Override
  public StatisticLog createStatisticLog(StatisticLog statisticLog) {
    statisticLog.setStatisticLogId(genUUID());
    return statisticLogDao.save(statisticLog);
  }

  @Override
  public void updateStatisticLog(StatisticLog statisticLog) {
    Optional<StatisticLog> statisticLogOld = statisticLogDao.findById(
        statisticLog.getStatisticLogId());
    if (statisticLogOld.isEmpty()) {
      throw new NotFoundException(STATISTIC_LOG_ID_NOT_FOUND);
    }
    statisticLogDao.save(statisticLog);
  }
}