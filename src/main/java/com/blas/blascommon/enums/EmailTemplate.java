package com.blas.blascommon.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {
  RESEND_KEY("resend-key"),
  PAYMENT_RECEIPT("payment-receipt"),
  ADD_CARD_SUCCESS("add-card-success"),
  REPORT_SPENDING("report-spending");

  private final String templateName;
}
