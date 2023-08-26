package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.File;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends JpaRepository<File, String> {

  @Query("SELECT f FROM File f")
  public List<File> getAllFile();

  @Query("SELECT f FROM File f WHERE f.isDelete = false AND f.userDetail.userId = :userId")
  public List<File> getAllActiveFileByUser(@Param("userId") String userId);

  @Query("SELECT f FROM File f WHERE f.isDelete = true AND f.userDetail.userId = :userId")
  public List<File> getAllDeletedFileByUser(@Param("userId") String userId);

  @Query("SELECT f FROM File f WHERE f.isDelete = false AND f.userDetail.userId = :userId AND f.fileName LIKE '%:keyword%'")
  public List<File> searchAllActiveFileByUser(@Param("userId") String userId,
      @Param("keyword") String keyword);

  @Query("SELECT f FROM File f WHERE f.isDelete = false AND f.isShareEveryone = true")
  public List<File> getAllValidPublicFile();

  @Query("SELECT f FROM File f WHERE f.userDetail.userId = :userId AND f.filePath = :filePath")
  public File getFileByUserIdAndFilePath(@Param("userId") String userId,
      @Param("filePath") String filePath);
}
