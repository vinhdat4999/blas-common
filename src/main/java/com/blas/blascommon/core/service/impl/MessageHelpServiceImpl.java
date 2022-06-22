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
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class MessageHelpServiceImpl implements MessageHelpService {

    @Autowired
    private HelpDao helpDao;

    @Autowired
    private MessageHelpDao messageHelpDao;

    @Override
    public List<MessageHelp> getAllMessageHelpByHelpTicketId(String ticketId) {
        if (helpDao.findById(ticketId).isEmpty()) {
            throw new NotFoundException(HELP_TICKET_ID_NOT_FOUND);
        }
        return messageHelpDao.getMessageHelpListByHelpTicketId(ticketId);
    }

    @Override
    public MessageHelp getMessageHelpById(String id) {
        Optional<MessageHelp> messageHelp = messageHelpDao.findById(id);
        if (messageHelp.isEmpty()) {
            throw new NotFoundException(MESSAGE_HELP_ID_NOT_FOUND);
        }
        return messageHelp.get();
    }

    @Override
    public MessageHelp createMessageHelp(MessageHelp messageHelp) {
        messageHelp.setId(genUUID());
        return messageHelpDao.save(messageHelp);
    }

    @Override
    public void updateMessageHelp(MessageHelp messageHelp) {
        if (messageHelpDao.findById(messageHelp.getId()).isEmpty()) {
            throw new NotFoundException(MESSAGE_HELP_ID_NOT_FOUND);
        }
        messageHelpDao.save(messageHelp);
    }
}
