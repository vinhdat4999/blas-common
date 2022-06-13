package com.blas.blascommon.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "file_shares")
public class FileShare {

    @Id
    @Column(name = "file_share_id", length = 50, nullable = false)
    @NotEmpty
    private String fileShareId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "file_id", foreignKey = @ForeignKey(name = "	fk_file_shares_1"))
    @NotNull
    private File file;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "	fk_file_shares_2"))
    @NotNull
    private UserDetail userDetail;

    public FileShare() {
    }

    public FileShare(String fileShareId, File file, UserDetail userDetail) {
        this.fileShareId = fileShareId;
        this.file = file;
        this.userDetail = userDetail;
    }

    public String getFileShareId() {
        return fileShareId;
    }

    public void setFileShareId(String fileShareId) {
        this.fileShareId = fileShareId;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    @Override
    public String toString() {
        return "FileShare{" +
                "fileShareId='" + fileShareId + '\'' +
                ", file=" + file +
                ", userDetail=" + userDetail +
                '}';
    }
}
