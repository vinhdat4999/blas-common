package com.blas.blascommon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IdContextHazelcastWrapper {

  private String message;
  private String globalId;
  private String callerId;
  private String callerServiceId;
  private String traceId;
}
