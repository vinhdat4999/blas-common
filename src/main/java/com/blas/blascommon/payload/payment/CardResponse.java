package com.blas.blascommon.payload.payment;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardResponse {

  private String statusCode;
  private String cardId;
  private String maskedCardNumber;
  private String cardType;
  private LocalDateTime addedTime;
  private String message;
}
