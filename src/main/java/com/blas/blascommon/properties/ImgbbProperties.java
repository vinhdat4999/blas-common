package com.blas.blascommon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blas.image.imgbb")
public class ImgbbProperties {

  private boolean enabled;
  private String url;
  private String privateKey;
  private long expirationTime = 0;
}
