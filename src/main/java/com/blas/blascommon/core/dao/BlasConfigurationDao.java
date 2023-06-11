package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.BlasConfiguration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasConfigurationDao extends JpaRepository<BlasConfiguration, String> {

  @Query("SELECT b FROM BlasConfiguration b WHERE b.isActive = true")
  public List<BlasConfiguration> getAllActiveBlasConfiguration();

  @Query("SELECT b FROM BlasConfiguration b WHERE b.configKey = ?1 AND b.isActive = true")
  public BlasConfiguration getBlasConfigurationByConfigKeyAndActive(String configKey);

  @Query("SELECT b FROM BlasConfiguration b WHERE b.configLabel1 IN ?1 AND b.configLabel2 IN ?1 AND b.configLabel3 IN ?1 AND b.isActive = true")
  public List<BlasConfiguration> getBlasConfigurationsByLabelAndActive(String... labels);
}
