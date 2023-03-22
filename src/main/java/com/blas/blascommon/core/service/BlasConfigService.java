package com.blas.blascommon.core.service;

import org.apache.commons.codec.DecoderException;

public interface BlasConfigService {

  public String getConfigValueFromKey(String key);

  public String transformValue(String rawStr) throws DecoderException;
}
