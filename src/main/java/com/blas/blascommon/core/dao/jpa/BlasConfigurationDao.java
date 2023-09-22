package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.BlasConfiguration;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlasConfigurationDao extends JpaRepository<BlasConfiguration, String> {

  @Query("SELECT b FROM BlasConfiguration b WHERE b.isActive = true")
  List<BlasConfiguration> getAllActiveBlasConfiguration();

  @Query("SELECT b FROM BlasConfiguration b WHERE b.configKey = :configKey AND b.isActive = true")
  BlasConfiguration getBlasConfigurationByConfigKeyAndActive(
      @Param("configKey") String configKey);

  @Query("SELECT b FROM BlasConfiguration b WHERE b.configLabel1 IN :labels AND b.configLabel2 IN :labels AND b.configLabel3 IN :labels AND b.isActive = true")
  List<BlasConfiguration> getBlasConfigurationsByLabelAndActive(
      @Param("labels") String... labels);
}
