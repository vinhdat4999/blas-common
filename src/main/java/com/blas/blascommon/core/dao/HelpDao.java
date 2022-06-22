package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.Help;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpDao extends JpaRepository<Help, String> {

    @Query("SELECT h FROM Help h WHERE h.createBy.userId = ?1")
    public List<Help> getHelpListByUserId(String userId);

    @Query("SELECT h FROM Help h WHERE h.status = ?1")
    public List<Help> getHelpListByStatus(String status);

}