package com.blas.blascommon.payload;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ChargeResponse {

  private String statusCode;
  private String transactionId;
  private LocalDateTime transactionTime;
  private String cardId;
  private String maskedCardNumber;
  private String cardType;
  private String username;
  private String amountCaptured;
  private String amountRefund;
  private String status;
  private boolean isRefundTransaction;
  private boolean isGuestCard;
  private String description;
  private String note;
}
