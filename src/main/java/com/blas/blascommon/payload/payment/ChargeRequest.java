package com.blas.blascommon.payload.payment;

import lombok.Data;

@Data
public class ChargeRequest {

  private long amount;
  private String description;
}
