package com.blas.blascommon.report;

import static com.blas.blascommon.report.ReportConstants.REPORT_LINE_LENGTH;
import static com.blas.blascommon.utils.datetimeutils.DateTimeUtils.STANDARD_DATE_TIME_FORMAT_1;
import static com.blas.blascommon.utils.datetimeutils.DateTimeUtils.convertLocalDateTimeToTimeFormat;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.rightPad;

import com.blas.blascommon.enums.ReportType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ReportGenerator {

  public static String generateReport(BlasReport blasReport) {
    List<String> content = new ArrayList<>();
    content.add(generateHeader());
    if (Objects.isNull(blasReport)) {
      content.add("EMPTY REPORT");
    } else {
      content.add(EMPTY);
      content.add(generateReportType(blasReport.getReportType()));
      content.add(generateTimeReport(blasReport.getTimeGenerated()));
      content.add(generateReportBy(blasReport.getReportBy()));
      content.add(EMPTY);
      content.addAll(blasReport.getReportMessages());
    }
    return standardizeReport(content);
  }

  private String generateHeader() {
    return String.format("COPYRIGHT © %d BLAS.", LocalDate.now().getYear());
  }

  private String generateReportType(ReportType reportType) {
    return String.format("REPORT TYPE: %s", reportType.getType());
  }

  private String generateTimeReport(LocalDateTime generatedTime) {
    return String.format("GENERATED REPORT TIME: %s",
        convertLocalDateTimeToTimeFormat(generatedTime, STANDARD_DATE_TIME_FORMAT_1));
  }

  private String generateReportBy(String reportBy) {
    return String.format("REPORT BY: %s", reportBy);
  }

  private String standardizeReport(List<String> list) {
    return list.stream()
        .map(line -> rightPad(line, REPORT_LINE_LENGTH))
        .collect(joining(System.lineSeparator()));
  }
}
