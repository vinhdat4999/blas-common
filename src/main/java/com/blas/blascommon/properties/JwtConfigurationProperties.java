package com.blas.blascommon.properties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
@ConfigurationProperties(prefix = "blas.blas-idp.jwt")
public class JwtConfigurationProperties {

  private String secret;
  private long timeToExpired;
}
