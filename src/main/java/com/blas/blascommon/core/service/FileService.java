package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.File;
import java.util.List;

public interface FileService {

  List<File> getAllFile();

  File getFileByUserIdAndFilePath(String userId, String filePath);

  List<File> getAllActiveFileByUser(String userId);

  List<File> searchAllActiveFileByUser(String userId, String keyword);

  List<File> getAllFileDeletedByUser(String userId);

  List<File> getAllValidPublicFile();

  File createFile(String userId, File file);

  void updateFile(String userId, File file);

  void deletePhysicalFile(String fileId);
}
