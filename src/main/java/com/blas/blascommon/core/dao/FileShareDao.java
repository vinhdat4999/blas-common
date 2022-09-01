package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.FileShare;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileShareDao extends JpaRepository<FileShare, String> {

  @Query("SELECT f FROM FileShare f WHERE f.file.fileId = ?1")
  public List<FileShare> getAllFileShareByFileId(String fileId);

  @Query("SELECT f FROM FileShare f WHERE f.file.filePath = ?1")
  public List<FileShare> getAllFileShareByFilePath(String filePath);

  @Query("SELECT f FROM FileShare f WHERE f.userDetail.userId = ?1")
  public List<FileShare> getAllFileShareByShareForThisUser(String userId);
}