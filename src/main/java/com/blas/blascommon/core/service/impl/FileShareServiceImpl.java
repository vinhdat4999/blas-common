package com.blas.blascommon.core.service.impl;

import static com.blas.blascommon.constants.Response.FILE_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.FILE_PATH_NOT_FOUND;
import static com.blas.blascommon.constants.Response.FILE_SHARE_ID_NOT_FOUND;
import static com.blas.blascommon.constants.Response.USER_ID_NOT_FOUND;
import static com.blas.blascommon.security.SecurityUtils.getUserIdLoggedIn;
import static com.blas.blascommon.utils.IdUtils.genUUID;

import com.blas.blascommon.core.dao.AuthUserDao;
import com.blas.blascommon.core.dao.FileDao;
import com.blas.blascommon.core.dao.FileShareDao;
import com.blas.blascommon.core.model.AuthUser;
import com.blas.blascommon.core.model.File;
import com.blas.blascommon.core.model.FileShare;
import com.blas.blascommon.core.service.AuthUserService;
import com.blas.blascommon.core.service.FileShareService;
import com.blas.blascommon.exceptions.types.NotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class, Throwable.class})
public class FileShareServiceImpl implements FileShareService {

    @Autowired
    private AuthUserDao authUserDao;

    @Autowired
    private FileDao fileDao;

    @Autowired
    private FileShareDao fileShareDao;

    @Autowired
    private AuthUserService authUserService;

    @Override
    public List<FileShare> getAllFileShareByFileId(String fileId) {
        Optional<File> file = fileDao.findById(fileId);
        if (file.isEmpty()) {
            throw new NotFoundException(FILE_ID_NOT_FOUND);
        }
        return fileShareDao.getAllFileShareByFileId(fileId);
    }

    @Override
    public List<FileShare> getAllFileShareByFilePath(String filePath) {
        File file = fileDao.getFileByUserIdAndFilePath(getUserIdLoggedIn(authUserService),
                filePath);
        if (file == null) {
            throw new NotFoundException(FILE_PATH_NOT_FOUND);
        }
        return fileShareDao.getAllFileShareByFilePath(filePath);
    }

    @Override
    public List<FileShare> getAllFileShareByShareForThisUser(String userId) {
        Optional<AuthUser> authUser = authUserDao.findById(userId);
        if (authUser.isEmpty()) {
            throw new NotFoundException(USER_ID_NOT_FOUND);
        }
        return fileShareDao.getAllFileShareByShareForThisUser(userId);
    }

    @Override
    public FileShare getFileShareByFileShareId(String fileShareId) {
        Optional<FileShare> fileShare = fileShareDao.findById(fileShareId);
        if (fileShare.isEmpty()) {
            throw new NotFoundException(FILE_SHARE_ID_NOT_FOUND);
        }
        return fileShare.get();
    }

    @Override
    public FileShare createFileShare(FileShare fileShare) {
        fileShare.setFileShareId(genUUID());
        return fileShareDao.save(fileShare);
    }

    @Override
    public void updateFileShare(FileShare fileShare) {
        Optional<FileShare> fileShareOld = fileShareDao.findById(fileShare.getFileShareId());
        if (fileShareOld.isEmpty()) {
            throw new NotFoundException(FILE_SHARE_ID_NOT_FOUND);
        }
        fileShareDao.save(fileShare);
    }

    @Override
    public void deletePhysicalFileShareByFileShareId(String fileShareId) {
        Optional<FileShare> fileShare = fileShareDao.findById(fileShareId);
        if (fileShare.isEmpty()) {
            throw new NotFoundException(FILE_SHARE_ID_NOT_FOUND);
        }
        fileShareDao.delete(fileShare.get());
    }

    @Override
    public void deletePhysicalFileShareByFileId(String fileId) {
        List<FileShare> fileShareList = getAllFileShareByFileId(fileId);
        for (FileShare fileShare : fileShareList) {
            fileShareDao.delete(fileShare);
        }
    }
}
