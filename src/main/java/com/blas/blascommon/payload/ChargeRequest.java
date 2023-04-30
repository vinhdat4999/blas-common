package com.blas.blascommon.payload;

import com.blas.blascommon.enums.Currency;
import lombok.Data;

@Data
public class ChargeRequest {

  private long amount;
  private Currency currency;
  private String cardId;
  private String cardNumber;
  private String cardHolder;
  private String expMonth;
  private String expYear;
  private String cvc;
  private String description;
}
