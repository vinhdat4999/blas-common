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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "files")
public class File {

  @Id
  @Column(name = "file_id", length = 50, nullable = false)
  @NotEmpty
  private String fileId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_files_1"))
  @NotNull
  private UserDetail userDetail;

  @Column(name = "file_name", length = 50, nullable = false)
  @NotEmpty
  private String fileName;

  @Column(name = "file_path", length = 300, nullable = false)
  @NotEmpty
  private String filePath;

  @Column(name = "time_upload", nullable = false)
  private LocalDateTime timeUpload;

  @Column(name = "description", length = 300, nullable = false)
  private String description;

  @Column(name = "is_delete")
  @NotEmpty
  private boolean isDelete;

  @Column(name = "is_share_everyone")
  @NotEmpty
  private boolean isShareEveryone;
}
