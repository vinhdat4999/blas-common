package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.FileShare;
import java.util.List;

public interface FileShareService {

    public List<FileShare> getAllFileShareByFileId(String fileId);

    public List<FileShare> getAllFileShareByFilePath(String filePath);

    public List<FileShare> getAllFileShareByShareForThisUser(String userId);

    public FileShare getFileShareByFileShareId(String fileShareId);

    public FileShare createFileShare(FileShare fileShare);

    public void updateFileShare(FileShare fileShare);

    public void deletePhysicalFileShareByFileShareId(String fileShareId);

    public void deletePhysicalFileShareByFileId(String fileId);

}
