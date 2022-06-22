package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.MessageHelp;
import java.util.List;

public interface MessageHelpService {

    public List<MessageHelp> getAllMessageHelpByHelpTicketId(String ticketId);

    public MessageHelp getMessageHelpById(String id);

    public MessageHelp createMessageHelp(MessageHelp messageHelp);

    public void updateMessageHelp(MessageHelp messageHelp);

}
