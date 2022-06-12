package com.blas.blascommon.core.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "files")
public class File {

    @Id
    @Column(name = "file_id", length = 50, nullable = false)
    private String fileId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "	fk_files_1"))
    private UserDetail userDetail;

    @Column(name = "file_name", length = 50, nullable = false)
    private String fileName;

    @Column(name = "file_path", length = 300, nullable = false)
    private String filePath;

    @Column(name = "time_upload", nullable = false)
    private LocalDateTime timeUpload;

    @Column(name = "description", length = 300, nullable = false)
    private String description;

    @Column(name = "is_delete")
    private boolean isDelete;

    @Column(name = "is_share_everyone")
    private boolean isShareEveryone;

    public File() {
    }

    public File(String fileId, UserDetail userDetail, String fileName, String filePath,
            LocalDateTime timeUpload, String description, boolean isDelete,
            boolean isShareEveryone) {
        this.fileId = fileId;
        this.userDetail = userDetail;
        this.fileName = fileName;
        this.filePath = filePath;
        this.timeUpload = timeUpload;
        this.description = description;
        this.isDelete = isDelete;
        this.isShareEveryone = isShareEveryone;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public LocalDateTime getTimeUpload() {
        return timeUpload;
    }

    public void setTimeUpload(LocalDateTime timeUpload) {
        this.timeUpload = timeUpload;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDelete() {
        return isDelete;
    }

    public void setDelete(boolean delete) {
        isDelete = delete;
    }

    public boolean isShareEveryone() {
        return isShareEveryone;
    }

    public void setShareEveryone(boolean shareEveryone) {
        isShareEveryone = shareEveryone;
    }

    @Override
    public String toString() {
        return "File{" +
                "fileId='" + fileId + '\'' +
                ", userDetail=" + userDetail +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", timeUpload=" + timeUpload +
                ", description='" + description + '\'' +
                ", isDelete=" + isDelete +
                ", isShareEveryone=" + isShareEveryone +
                '}';
    }
}