package com.blas.blascommon.payload;

import com.blas.blascommon.enums.Currency;
import lombok.Data;

@Data
public class GuestChargeRequest {

  private long amount;
  private Currency currency;
  private CardRequest cardRequest;
  private String description;
}
