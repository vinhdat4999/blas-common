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
@ConfigurationProperties(prefix = "blas.blas-email")
public class EmailConfigurationProperties {

  private String emailSender;
  private String password;
  private int portSender;
}
