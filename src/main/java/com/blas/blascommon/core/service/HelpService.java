package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.Help;
import java.util.List;

public interface HelpService {

    public List<Help> getAllHelpByUser(String userId);

    public List<Help> getAllHelpByStatus(String status);

    public List<Help> getAllHelpByUserAndStatus(String userId, String status);

    public Help getHelpByHelpTicketId(String ticketId);

    public Help createHelp(Help help);

    public void updateHelp(Help help);

}
