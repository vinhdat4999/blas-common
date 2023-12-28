package com.blas.blascommon.payload.payment;

import com.blas.blascommon.enums.Currency;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class VnPayChargeRequest extends ChargeRequest {

  private String bankCode;
  private Currency currency;
}
