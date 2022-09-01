package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.MessageHelp;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageHelpDao extends JpaRepository<MessageHelp, String> {

  @Query("SELECT m FROM MessageHelp m WHERE m.ticket_id.ticketId = ?1")
  public List<MessageHelp> getMessageHelpListByHelpTicketId(String ticketId);
}