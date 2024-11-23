package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.HELP_TICKET_ID_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.idutils.IdUtils.genMixID;

import com.blas.blascommon.core.dao.jpa.AuthUserDao;
import com.blas.blascommon.core.dao.jpa.HelpDao;
import com.blas.blascommon.core.model.Help;
import com.blas.blascommon.core.service.HelpService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class HelpServiceImpl implements HelpService {

  @Lazy
  private final HelpDao helpDao;

  @Lazy
  private final AuthUserDao authUserDao;

  @Override
  public List<Help> getAllHelpByUser(String userId) {
    if (authUserDao.existsById(userId)) {
      return helpDao.getHelpListByUserId(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
  }

  @Override
  public List<Help> getAllHelpByStatus(String status) {
    return helpDao.getHelpListByStatus(status);
  }

  @Override
  public List<Help> getAllHelpByUserAndStatus(String userId, String status) {
    if (authUserDao.existsById(userId)) {
      return helpDao.getHelpListByStatus(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
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
    if (helpDao.existsById(help.getTicketId())) {
      helpDao.save(help);
    }
    throw new NotFoundException(HELP_TICKET_ID_NOT_FOUND);
  }
}
