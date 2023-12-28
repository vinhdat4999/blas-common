package com.blas.blascommon.payload.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

  private String cardNumber;
  private String cardHolder;
  private String expMonth;
  private String expYear;
  private String cvc;
}
