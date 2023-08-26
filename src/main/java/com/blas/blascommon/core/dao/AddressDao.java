package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<Address, String> {

  @Query("SELECT a FROM Address a WHERE a.userDetail.userId = :userId AND a.isDefault = true")
  public Address getDefaultAddressByUser(@Param("userId") String userId);

  @Query("SELECT a FROM Address a WHERE a.isActive = true AND a.userDetail.userId = :userId")
  public List<Address> getAllActiveAddressByUser(@Param("userId") String userId);
}
