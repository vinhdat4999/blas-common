package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.BlasConfiguration;
import java.util.List;

public interface BlasConfigurationService {

  public List<BlasConfiguration> getAllActiveBlasConfiguration();

  public BlasConfiguration getBlasConfigurationByConfigKeyAndActive(String configKey);

  public List<BlasConfiguration> getBlasConfigurationsByLabelAndActive(String... labels);
}
