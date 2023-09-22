package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.Help;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HelpDao extends JpaRepository<Help, String> {

  @Query("SELECT h FROM Help h WHERE h.createBy.userId = :userId")
  List<Help> getHelpListByUserId(@Param("userId") String userId);

  @Query("SELECT h FROM Help h WHERE h.status = :status")
  List<Help> getHelpListByStatus(@Param("status") String status);

  @Query("SELECT count(1)>0 FROM Help h WHERE h.ticketId = :ticketId")
  boolean isExistedHelpTicketId(@Param("ticketId") String ticketId);
}
