package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Help;
import java.util.List;

public interface HelpService {

  List<Help> getAllHelpByUser(String userId);

  List<Help> getAllHelpByStatus(String status);

  List<Help> getAllHelpByUserAndStatus(String userId, String status);

  Help getHelpByHelpTicketId(String ticketId);

  Help createHelp(Help help);

  void updateHelp(Help help);
}
