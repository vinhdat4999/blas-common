package com.blas.blascommon.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MessageTopic {

  public static final String BLAS_EMAIL_QUEUE = "blas-email-queue";
  public static final String BLAS_RECEIVER_BANK_INFO_MAP = "blas-receiver-bank-info-queue";
  public static final String BLAS_IDP_SEND_AUTHENTICATION_CODE_TOPIC = "blas-idp-send-authentication-topic";
  public static final String BLAS_PAYMENT_GATEWAY_ADD_CARD_TOPIC = "blas-payment-gateway-add-card-topic";
  public static final String BLAS_PAYMENT_GATEWAY_RECEIPT_TOPIC = "blas-payment-gateway-receipt-topic";
}
