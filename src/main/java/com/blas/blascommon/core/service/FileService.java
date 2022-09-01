package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.File;
import java.util.List;

public interface FileService {

  public List<File> getAllFile();

  public File getFileByUserIdAndFilePath(String userId, String filePath);

  public List<File> getAllActiveFileByUser(String userId);

  public List<File> searchAllActiveFileByUser(String userId, String keyword);

  public List<File> getAllFileDeletedByUser(String userId);

  public List<File> getAllValidPublicFile();

  public File createFile(String userId, File file);

  public void updateFile(String userId, File file);

  public void deletePhysicalFile(String fileId);
}