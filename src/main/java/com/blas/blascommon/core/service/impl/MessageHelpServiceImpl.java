package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.HELP_TICKET_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.MESSAGE_HELP_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.HelpDao;
import com.blas.blascommon.core.dao.MessageHelpDao;
import com.blas.blascommon.core.model.MessageHelp;
import com.blas.blascommon.core.service.MessageHelpService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class MessageHelpServiceImpl implements MessageHelpService {

  @Lazy
  private final HelpDao helpDao;

  @Lazy
  private final MessageHelpDao messageHelpDao;

  public MessageHelpServiceImpl(HelpDao helpDao, MessageHelpDao messageHelpDao) {
    this.helpDao = helpDao;
    this.messageHelpDao = messageHelpDao;
  }

  @Override
  public List<MessageHelp> getAllMessageHelpByHelpTicketId(String ticketId) {
    helpDao.findById(ticketId).orElseThrow(() -> new NotFoundException(HELP_TICKET_ID_NOT_FOUND));
    return messageHelpDao.getMessageHelpListByHelpTicketId(ticketId);
  }

  @Override
  public MessageHelp getMessageHelpById(String id) {
    return messageHelpDao.findById(id)
        .orElseThrow(() -> new NotFoundException(MESSAGE_HELP_ID_NOT_FOUND));
  }

  @Override
  public MessageHelp createMessageHelp(MessageHelp messageHelp) {
    messageHelp.setId(genUUID());
    return messageHelpDao.save(messageHelp);
  }

  @Override
  public void updateMessageHelp(MessageHelp messageHelp) {
    messageHelpDao.findById(messageHelp.getId())
        .orElseThrow(() -> new NotFoundException(MESSAGE_HELP_ID_NOT_FOUND));
    messageHelpDao.save(messageHelp);
  }
}
