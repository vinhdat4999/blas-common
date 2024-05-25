package com.blas.blascommon.payload.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StripeAddedChargeRequest extends StripeChargeRequest {

  private String cardId;
}
