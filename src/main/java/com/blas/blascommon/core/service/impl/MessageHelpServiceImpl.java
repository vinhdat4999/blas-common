package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.HELP_TICKET_ID_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.MESSAGE_HELP_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.jpa.HelpDao;
import com.blas.blascommon.core.dao.jpa.MessageHelpDao;
import com.blas.blascommon.core.model.MessageHelp;
import com.blas.blascommon.core.service.MessageHelpService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class MessageHelpServiceImpl implements MessageHelpService {

  @Lazy
  private final HelpDao helpDao;

  @Lazy
  private final MessageHelpDao messageHelpDao;

  @Override
  public List<MessageHelp> getAllMessageHelpByHelpTicketId(String ticketId) {
    if (helpDao.existsById(ticketId)) {
      return messageHelpDao.getMessageHelpListByHelpTicketId(ticketId);
    }
    throw new NotFoundException(HELP_TICKET_ID_NOT_FOUND);
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
    if (messageHelpDao.existsById(messageHelp.getId())) {
      messageHelpDao.save(messageHelp);
    }
    throw new NotFoundException(MESSAGE_HELP_ID_NOT_FOUND);
  }
}
