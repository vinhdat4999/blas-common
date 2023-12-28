package com.blas.blascommon.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmailTemplate {
  RESEND_KEY("resend-key"),
  STRIPE_PAYMENT_RECEIPT("stripe-payment-receipt"),
  VNPAY_PAYMENT_RECEIPT("vnpay-payment-receipt"),
  ADD_CARD_SUCCESS("add-card-success"),
  REPORT_SPENDING("report-spending");

  private final String templateName;
}
