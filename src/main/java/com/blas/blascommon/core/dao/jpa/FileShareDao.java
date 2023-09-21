package com.blas.blascommon.core.dao.jpa;

import com.blas.blascommon.core.model.FileShare;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileShareDao extends JpaRepository<FileShare, String> {

  @Query("SELECT f FROM FileShare f WHERE f.file.fileId = :fileId")
  public List<FileShare> getAllFileShareByFileId(@Param("fileId") String fileId);

  @Query("SELECT f FROM FileShare f WHERE f.file.filePath = :filePath")
  public List<FileShare> getAllFileShareByFilePath(@Param("filePath") String filePath);

  @Query("SELECT f FROM FileShare f WHERE f.userDetail.userId = :userId")
  public List<FileShare> getAllFileShareByShareForThisUser(@Param("userId") String userId);
}
