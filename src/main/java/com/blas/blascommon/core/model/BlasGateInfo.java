package com.blas.blascommon.core.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
@Table(name = "blas_gate_infos")
public class BlasGateInfo {

  @Id
  @Column(name = "id", length = 50, nullable = false)
  @NotEmpty
  private String id;

  @Column(name = "service", length = 20, nullable = false)
  private String service;

  @Column(name = "time_logged")
  @NotNull
  private LocalDateTime timeLogged;

  @Column(name = "locale", length = 20, nullable = false)
  private String locale;

  @Column(name = "remote_user", length = 20, nullable = false)
  private String remoteUser;

  @Column(name = "remote_address", length = 20, nullable = false)
  private String remoteAddress;

  @Column(name = "request_url", length = 200, nullable = false)
  private String requestUrl;

  @Column(name = "remote_port", length = 8, nullable = false)
  private String remotePort;

  @Column(name = "query_string", length = 200, nullable = false)
  private String queryString;
}
