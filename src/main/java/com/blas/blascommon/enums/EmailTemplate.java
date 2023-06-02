package com.blas.blascommon.enums;

public enum EmailTemplate {
  RESEND_KEY("resend-key"),
  PAYMENT_RECEIPT("payment-receipt"),
  ADD_CARD_SUCCESS("add-card-success"),
  REPORT_SPENDING("report-spending");

  private final String templateName;

  EmailTemplate(String templateName) {
    this.templateName = templateName;
  }

  public String getTemplateName() {
    return templateName;
  }
}
