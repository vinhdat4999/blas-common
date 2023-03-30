package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.FILE_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.FILE_PATH_NOT_FOUND;
import static com.blas.blascommon.constants.Response.FILE_SHARE_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.security.SecurityUtils.getUserIdLoggedIn;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.FileDao;
import com.blas.blascommon.core.dao.FileShareDao;
import com.blas.blascommon.core.model.File;
import com.blas.blascommon.core.model.FileShare;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.core.service.FileShareService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class FileShareServiceImpl implements FileShareService {

  @Lazy
  @Autowired
  private AuthUserDao authUserDao;

  @Lazy
  @Autowired
  private FileDao fileDao;

  @Lazy
  @Autowired
  private FileShareDao fileShareDao;

  @Lazy
  @Autowired
  private AuthUserService authUserService;

  @Override
  public List<FileShare> getAllFileShareByFileId(String fileId) {
    fileDao.findById(fileId).orElseThrow(() -> new NotFoundException(FILE_ID_NOT_FOUND));
    return fileShareDao.getAllFileShareByFileId(fileId);
  }

  @Override
  public List<FileShare> getAllFileShareByFilePath(String filePath) {
    File file = fileDao.getFileByUserIdAndFilePath(getUserIdLoggedIn(authUserService), filePath);
    if (file == null) {
      throw new NotFoundException(FILE_PATH_NOT_FOUND);
    }
    return fileShareDao.getAllFileShareByFilePath(filePath);
  }

  @Override
  public List<FileShare> getAllFileShareByShareForThisUser(String userId) {
    authUserDao.findById(userId).orElseThrow(() -> new NotFoundException(USER_ID_NOT_FOUND));
    return fileShareDao.getAllFileShareByShareForThisUser(userId);
  }

  @Override
  public FileShare getFileShareByFileShareId(String fileShareId) {
    return fileShareDao.findById(fileShareId)
        .orElseThrow(() -> new NotFoundException(FILE_SHARE_ID_NOT_FOUND));
  }

  @Override
  public FileShare createFileShare(FileShare fileShare) {
    fileShare.setFileShareId(genUUID());
    return fileShareDao.save(fileShare);
  }

  @Override
  public void updateFileShare(FileShare fileShare) {
    fileShareDao.findById(fileShare.getFileShareId())
        .orElseThrow(() -> new NotFoundException(FILE_SHARE_ID_NOT_FOUND));
    fileShareDao.save(fileShare);
  }

  @Override
  public void deletePhysicalFileShareByFileShareId(String fileShareId) {
    fileShareDao.delete(fileShareDao.findById(fileShareId)
        .orElseThrow(() -> new NotFoundException(FILE_SHARE_ID_NOT_FOUND)));
  }

  @Override
  public void deletePhysicalFileShareByFileId(String fileId) {
    List<FileShare> fileShareList = getAllFileShareByFileId(fileId);
    fileShareDao.deleteAll(fileShareList);
  }
}
