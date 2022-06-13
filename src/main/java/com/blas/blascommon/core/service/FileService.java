package com.blas.blascommon.core.service;

import com.blas.blascommon.core.model.File;
import java.util.List;

public interface FileService {

    public File getFileByFilePath(String filePath);

    public List<File> getAllActiveFileByUser(String userId);

    public List<File> searchAllActiveFileByUser(String userId, String keyword);

    public List<File> getAllFileDeletedByUser(String userId);

    public File createFile(File file);

    public void updateFile(File file);

    public void deleteLogicFile(String fileId);

    public void deletePhysicalFile(String fileId);

}
