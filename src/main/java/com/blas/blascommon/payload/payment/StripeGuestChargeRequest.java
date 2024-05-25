package com.blas.blascommon.payload.payment;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class StripeGuestChargeRequest extends StripeChargeRequest {

  private CardRequest cardRequest;
}
