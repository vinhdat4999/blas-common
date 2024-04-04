package com.blas.blascommon.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "blas.hazelcast")
public class HazelcastConfiguration {

  private String aksNamespace;
  private String hazelcastService;
}
