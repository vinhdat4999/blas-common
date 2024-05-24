package com.blas.blascommon.core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Document(collection = "blas_gate_infos")
public class BlasGateInfo {

  @Id
  @NotEmpty
  private String globalId;

  private String service;

  @Field(name = "time_logged")
  @NotNull
  private LocalDateTime timeLogged;

  private String locale;

  @Field(name = "remote_user")
  private String remoteUser;

  @Field(name = "remote_address")
  private String remoteAddress;

  @Field(name = "request_url")
  private String requestUrl;

  @Field(name = "user_agent")
  private String userAgent;

  @Field(name = "query_string")
  private String queryString;
}
