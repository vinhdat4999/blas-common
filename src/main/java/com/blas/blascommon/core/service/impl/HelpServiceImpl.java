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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class HelpServiceImpl implements HelpService {

  @Lazy
  @Autowired
  private HelpDao helpDao;

  @Lazy
  @Autowired
  private AuthUserDao authUserDao;

  @Override
  public List<Help> getAllHelpByUser(String userId) {
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return helpDao.getHelpListByUserId(userId);
  }

  @Override
  public List<Help> getAllHelpByStatus(String status) {
    return helpDao.getHelpListByStatus(status);
  }

  @Override
  public List<Help> getAllHelpByUserAndStatus(String userId, String status) {
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return helpDao.getHelpListByStatus(userId);
  }

  @Override
  public Help getHelpByHelpTicketId(String ticketId) {
    return helpDao.findById(ticketId)
        .orElseThrow(() -> new NotFoundException(HELP_TICKET_ID_NOT_FOUND));
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
    helpDao.findById(help.getTicketId())
        .orElseThrow(() -> new NotFoundException(HELP_TICKET_ID_NOT_FOUND));
    helpDao.save(help);
  }
}
