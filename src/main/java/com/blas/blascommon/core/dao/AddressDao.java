package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.Address;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDao extends JpaRepository<Address, String> {

    @Query("SELECT a FROM Address a WHERE a.isActive = false")
    public List<Address> getAllActiveAddress();

    @Query("SELECT a FROM Address a WHERE a.isActive = false AND a.userDetail.userId = ?1")
    public List<Address> getAllActiveAddressByUser(String userId);

}