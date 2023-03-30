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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "file_shares")
public class FileShare {

  @Id
  @Column(name = "file_share_id", length = 50, nullable = false)
  @NotEmpty
  private String fileShareId;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "file_id", foreignKey = @ForeignKey(name = "fk_file_shares_1"))
  @NotNull
  private File file;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_file_shares_2"))
  @NotNull
  private UserDetail userDetail;
}
