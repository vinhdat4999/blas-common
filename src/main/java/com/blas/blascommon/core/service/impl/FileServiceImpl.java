package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.DUPLICATED_FILE;
import static com.blas.blascommon.constants.Response.FILE_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.FILE_PATH_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.FileDao;
import com.blas.blascommon.core.model.File;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.core.service.FileService;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class FileServiceImpl implements FileService {

  @Autowired
  private AuthUserDao authUserDao;

  @Autowired
  private FileDao fileDao;

  @Autowired
  private AuthUserService authUserService;

  @Override
  public List<File> getAllFile() {
    return fileDao.getAllFile();
  }

  @Override
  public File getFileByUserIdAndFilePath(String userId, String filePath) {
    File file = fileDao.getFileByUserIdAndFilePath(userId, filePath);
    if (file == null) {
      throw new NotFoundException(FILE_PATH_NOT_FOUND);
    }
    return file;
  }

  @Override
  public List<File> getAllActiveFileByUser(String userId) {
    if (authUserDao.findById(userId).isEmpty()) {
      throw new NotFoundException(USER_ID_NOT_FOUND);
    }
    return fileDao.getAllActiveFileByUser(userId);
  }

  @Override
  public List<File> searchAllActiveFileByUser(String userId, String keyword) {
    if (authUserDao.findById(userId).isEmpty()) {
      throw new NotFoundException(USER_ID_NOT_FOUND);
    }
    return fileDao.searchAllActiveFileByUser(userId, keyword);
  }

  @Override
  public List<File> getAllFileDeletedByUser(String userId) {
    if (authUserDao.findById(userId).isEmpty()) {
      throw new NotFoundException(USER_ID_NOT_FOUND);
    }
    return fileDao.getAllDeletedFileByUser(userId);
  }

  @Override
  public List<File> getAllValidPublicFile() {
    return fileDao.getAllValidPublicFile();
  }

  @Override
  public File createFile(String userId, File file) {
    if (getFileByUserIdAndFilePath(userId, file.getFilePath()) != null) {
      throw new BadRequestException(DUPLICATED_FILE);
    }
    file.setFileId(genUUID());
    return fileDao.save(file);
  }

  @Override
  public void updateFile(String userId, File file) {
    Optional<File> fileOld = fileDao.findById(file.getFileId());
    if (fileOld.isEmpty()) {
      throw new NotFoundException(FILE_ID_NOT_FOUND);
    }
    File fileOldObject = getFileByUserIdAndFilePath(userId, file.getFilePath());
    if (fileOldObject != null && !fileOldObject.getFileId().equals(file.getFileId())) {
      throw new BadRequestException(DUPLICATED_FILE);
    }
    fileDao.save(file);
  }

  @Override
  public void deletePhysicalFile(String fileId) {
    Optional<File> file = fileDao.findById(fileId);
    if (file.isEmpty()) {
      throw new NotFoundException(FILE_ID_NOT_FOUND);
    }
    fileDao.delete(file.get());
  }
}
