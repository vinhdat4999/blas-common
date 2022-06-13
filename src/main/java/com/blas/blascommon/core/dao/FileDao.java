package com.blas.blascommon.core.dao;

import com.blas.blascommon.core.model.File;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends JpaRepository<File, String> {

    @Query("SELECT f FROM File f")
    public List<File> getAllFile();

    @Query("SELECT f FROM File f WHERE f.isDelete = false AND f.userDetail.userId = ?1")
    public List<File> getAllActiveFileByUser(String userId);

    @Query("SELECT f FROM File f WHERE f.isDelete = true AND f.userDetail.userId = ?1")
    public List<File> getAllDeletedFileByUser(String userId);

    @Query("SELECT f FROM File f WHERE f.isDelete = false AND f.userDetail.userId = ?1 AND f.fileName LIKE '%?2%'")
    public List<File> searchAllActiveFileByUser(String userId, String keyword);

    @Query("SELECT f FROM File f WHERE f.isDelete = false AND f.isShareEveryone = true")
    public List<File> getAllValidPublicFile();

}