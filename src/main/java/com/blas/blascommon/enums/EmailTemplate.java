package com.blas.blascommon.enums;

import com.blas.blascommon.exceptions.types.BadRequestException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {
  RESEND_KEY("resend-key"),
  STRIPE_PAYMENT_RECEIPT("stripe-payment-receipt"),
  VNPAY_PAYMENT_RECEIPT("vnpay-payment-receipt"),
  ADD_CARD_SUCCESS("add-card-success"),
  REPORT_SPENDING("report-spending"),
  ERROR_ALERT("error-alert"),
  REMIND_DEBT("remind-debt"),
  BANKING_PAYMENT_RECEIPT("banking-payment-receipt");

  private static final String INVALID_EMAIL_TEMPLATE = "Email template not found";
  private final String templateName;

  public static EmailTemplate getEmailTemplate(String templateName) {
    try {
      return EmailTemplate.valueOf(templateName);
    } catch (IllegalArgumentException exception) {
      throw new BadRequestException(INVALID_EMAIL_TEMPLATE);
    }
  }
}
