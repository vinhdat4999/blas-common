package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.REPORT_CODE_NOT_FOUND;

import com.blas.blascommon.core.dao.jpa.ReportDao;
import com.blas.blascommon.core.model.Report;
import com.blas.blascommon.core.service.ReportService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import com.blas.blascommon.utils.IdUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class ReportServiceImpl implements ReportService {

  @Lazy
  private final ReportDao reportDao;

  @Override
  public String generateReportCode() {
    String reportCode;
    do {
      reportCode = IdUtils.genMixID(10);
    } while (findReportByReportCode(reportCode) != null);
    return reportCode;
  }

  @Override
  public Report findReportByReportCode(String reportCode) {
    return reportDao.findById(reportCode)
        .orElse(null);
  }

  @Override
  public Report getReportByReportCode(String reportCode) {
    return reportDao.findById(reportCode)
        .orElseThrow(() -> new NotFoundException(REPORT_CODE_NOT_FOUND));
  }

  @Override
  public Report createReport(Report report) {
    return reportDao.save(report);
  }

  @Override
  public Report updateReport(Report report) {
    if (reportDao.existsById(report.getReportCode())) {
      return reportDao.save(report);
    }
    throw new NotFoundException(REPORT_CODE_NOT_FOUND);
  }
}
