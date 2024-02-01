package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Report;

public interface ReportService {

  Report findReportByReportCode(String reportCode);

  Report getReportByReportCode(String reportCode);

  Report createReport(Report report);

  Report updateReport(Report report);
}
