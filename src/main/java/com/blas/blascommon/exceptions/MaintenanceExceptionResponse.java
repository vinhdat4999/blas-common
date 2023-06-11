package com.blas.blascommon.exceptions;

import com.blas.blascommon.payload.MaintenanceTimeResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class MaintenanceExceptionResponse extends ExceptionResponse {

  private MaintenanceTimeResponse maintenanceTimeResponse;
}
