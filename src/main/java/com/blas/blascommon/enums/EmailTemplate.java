package com.blas.blascommon.enums;

public enum EmailTemplate {
  RESEND_KEY("resend-key");

  private final String templateName;

  EmailTemplate(String templateName) {
    this.templateName = templateName;
  }

  public String getTemplateName() {
    return templateName;
  }
}
