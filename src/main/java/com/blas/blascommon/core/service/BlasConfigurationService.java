package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.BlasConfiguration;
import java.util.List;

public interface BlasConfigurationService {

  List<BlasConfiguration> getAllActiveBlasConfiguration();

  BlasConfiguration getBlasConfigurationByConfigKeyAndActive(String configKey);

  List<BlasConfiguration> getBlasConfigurationsByLabelAndActive(String... labels);
}
