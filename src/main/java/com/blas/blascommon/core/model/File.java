package com.blas.blascommon.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
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
  @NotNull
  private boolean isDelete;

  @Column(name = "is_share_everyone")
  @NotNull
  private boolean isShareEveryone;
}
