package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.HELP_TICKET_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genMixID;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.HelpDao;
import com.blas.blascommon.core.model.Help;
import com.blas.blascommon.core.service.HelpService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class HelpServiceImpl implements HelpService {

    @Autowired
    private HelpDao helpDao;

    @Autowired
    private AuthUserDao authUserDao;

    @Override
    public List<Help> getAllHelpByUser(String userId) {
        if (authUserDao.findById(userId).isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return helpDao.getHelpListByUserId(userId);
    }

    @Override
    public List<Help> getAllHelpByStatus(String status) {
        return helpDao.getHelpListByStatus(status);
    }

    @Override
    public List<Help> getAllHelpByUserAndStatus(String userId, String status) {
        if (authUserDao.findById(userId).isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return helpDao.getHelpListByStatus(userId);
    }

    @Override
    public Help getHelpByHelpTicketId(String ticketId) {
        Optional<Help> help = helpDao.findById(ticketId);
        if (help.isEmpty()) {
            throw new NotFoundException(HELP_TICKET_ID_NOT_FOUND);
        }
        return help.get();
    }

    @Override
    public Help createHelp(Help help) {
        String ticketId;
        do {
            ticketId = genMixID();
        } while (helpDao.isExistedHelpTicketId(ticketId));
        help.setTicketId(ticketId);
        return helpDao.save(help);
    }

    @Override
    public void updateHelp(Help help) {
        if (helpDao.findById(help.getTicketId()).isEmpty()) {
            throw new NotFoundException(HELP_TICKET_ID_NOT_FOUND);
        }
        helpDao.save(help);
    }
}
