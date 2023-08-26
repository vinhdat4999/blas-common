package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.MessageHelp;
import java.util.List;

public interface MessageHelpService {

  List<MessageHelp> getAllMessageHelpByHelpTicketId(String ticketId);

  MessageHelp getMessageHelpById(String id);

  MessageHelp createMessageHelp(MessageHelp messageHelp);

  void updateMessageHelp(MessageHelp messageHelp);
}
