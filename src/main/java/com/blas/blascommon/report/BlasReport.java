package com.blas.blascommon.report;

import static org.apache.commons.lang3.StringUtils.EMPTY;

import com.blas.blascommon.enums.ReportType;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BlasReport {

  private ReportType reportType;
  private String reportCode;
  private LocalDateTime timeGenerated;
  private String reportBy;
  private List<String> reportMessages;

  public void addReportMessage(String message) {
    this.reportMessages.add(message);
  }

  public void addLineSeparator(int numOfLineSeparators) {
    for (int i = 0; i < numOfLineSeparators; i++) {
      this.reportMessages.add(EMPTY);
    }
  }

  public void addLineSeparator() {
    this.addLineSeparator(1);
  }
}
