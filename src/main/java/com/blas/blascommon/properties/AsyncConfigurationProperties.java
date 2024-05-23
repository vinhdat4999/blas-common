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
@ConfigurationProperties(prefix = "spring.task.execution.pool")
public class AsyncConfigurationProperties {

  private static final int DEFAULT_CORE_POOL_SIZE = 2;
  private static final int DEFAULT_MAX_POOL_SIZE = 5;
  private static final int DEFAULT_QUEUE_CAPACITY = 1000;

  private int coreSize = DEFAULT_CORE_POOL_SIZE;
  private int maxSize = DEFAULT_MAX_POOL_SIZE;
  private int queueCapacity = DEFAULT_QUEUE_CAPACITY;
}
