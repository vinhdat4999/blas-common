package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.FileShare;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface FileShareDao extends JpaRepository<FileShare, String> {

    @Query("SELECT f FROM FileShare f WHERE f.File.fileId = ?1")
    public List<FileShare> getAllFileShareByFileId(String fileId);

    @Query("SELECT f FROM FileShare f WHERE f.UserDetail.userId = ?1")
    public List<FileShare> getAllFileShareByShareForThisUser(String userId);

}