package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.FileShare;
import java.util.List;

public interface FileShareService {

  List<FileShare> getAllFileShareByFileId(String fileId);

  List<FileShare> getAllFileShareByFilePath(String filePath);

  List<FileShare> getAllFileShareByShareForThisUser(String userId);

  FileShare getFileShareByFileShareId(String fileShareId);

  FileShare createFileShare(FileShare fileShare);

  void updateFileShare(FileShare fileShare);

  void deletePhysicalFileShareByFileShareId(String fileShareId);

  void deletePhysicalFileShareByFileId(String fileId);
}
