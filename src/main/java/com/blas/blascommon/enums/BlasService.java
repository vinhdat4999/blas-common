package com.blas.blascommon.enums;

public enum BlasService {

  BLAS_COMMON("blas-common"),
  BLAS_IDP("blas-idp"),
  BLAS_GROW("blas-grow"),
  BLAS_EMAIL("blas-email");

  private final String serviceName;

  BlasService(String serviceName) {
    this.serviceName = serviceName;
  }

  public String getServiceName() {
    return serviceName;
  }
}
