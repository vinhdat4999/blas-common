package com.blas.blascommon.core.service;

public interface BlasConfigService {

  public String getConfigValueFromKey(String key);

  public String transformValue(String rawStr);
}
