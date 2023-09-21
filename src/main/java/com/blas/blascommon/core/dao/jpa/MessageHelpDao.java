package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.MessageHelp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageHelpDao extends JpaRepository<MessageHelp, String> {

  @Query("SELECT m FROM MessageHelp m WHERE m.ticketId = :ticketId")
  public List<MessageHelp> getMessageHelpListByHelpTicketId(@Param("ticketId") String ticketId);
}
