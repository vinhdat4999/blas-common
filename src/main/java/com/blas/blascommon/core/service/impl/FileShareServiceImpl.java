package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.ResponseMessage.FILE_PATH_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.FILE_SHARE_ID_NOT_FOUND;
import static com.blas.blascommon.constants.ResponseMessage.USER_ID_NOT_FOUND;
import static com.blas.blascommon.security.SecurityUtils.getUserIdLoggedIn;
import static com.blas.blascommon.utils.idutils.IdUtils.genUniqueId;

import com.blas.blascommon.core.dao.jpa.AuthUserDao;
import com.blas.blascommon.core.dao.jpa.FileDao;
import com.blas.blascommon.core.dao.jpa.FileShareDao;
import com.blas.blascommon.core.model.File;
import com.blas.blascommon.core.model.FileShare;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.core.service.FileShareService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class FileShareServiceImpl implements FileShareService {

  @Lazy
  private final AuthUserDao authUserDao;

  @Lazy
  private final FileDao fileDao;

  @Lazy
  private final FileShareDao fileShareDao;

  @Lazy
  private final AuthUserService authUserService;

  @Override
  public List<FileShare> getAllFileShareByFileId(String fileId) {
    if (fileDao.existsById(fileId)) {
      return fileShareDao.getAllFileShareByFileId(fileId);
    }
    throw new NotFoundException(FILE_SHARE_ID_NOT_FOUND);
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
    if (authUserDao.existsById(userId)) {
      return fileShareDao.getAllFileShareByShareForThisUser(userId);
    }
    throw new NotFoundException(USER_ID_NOT_FOUND);
  }

  @Override
  public FileShare getFileShareByFileShareId(String fileShareId) {
    return fileShareDao.findById(fileShareId)
        .orElseThrow(() -> new NotFoundException(FILE_SHARE_ID_NOT_FOUND));
  }

  @Override
  public FileShare createFileShare(FileShare fileShare) {
    fileShare.setFileShareId(genUniqueId());
    return fileShareDao.save(fileShare);
  }

  @Override
  public void updateFileShare(FileShare fileShare) {
    if (fileShareDao.existsById(fileShare.getFileShareId())) {
      fileShareDao.save(fileShare);
    }
    throw new NotFoundException(FILE_SHARE_ID_NOT_FOUND);
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
