package com.blas.blascommon.core.service.impl;

import com.blas.blascommon.core.dao.BlasConfigurationDao;
import com.blas.blascommon.core.model.BlasConfiguration;
import com.blas.blascommon.core.service.BlasConfigurationService;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class BlasConfigurationServiceImpl implements BlasConfigurationService {

  @Lazy
  private final BlasConfigurationDao blasConfigurationDao;

  public BlasConfigurationServiceImpl(BlasConfigurationDao blasConfigurationDao) {
    this.blasConfigurationDao = blasConfigurationDao;
  }

  @Override
  public List<BlasConfiguration> getAllActiveBlasConfiguration() {
    return blasConfigurationDao.getAllActiveBlasConfiguration();
  }

  @Override
  public BlasConfiguration getBlasConfigurationByConfigKeyAndActive(String configKey) {
    return blasConfigurationDao.getBlasConfigurationByConfigKeyAndActive(configKey);
  }

  @Override
  public List<BlasConfiguration> getBlasConfigurationsByLabelAndActive(String... labels) {
    return blasConfigurationDao.getBlasConfigurationsByLabelAndActive(labels);
  }
}
