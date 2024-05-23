package com.blas.blascommon.configurations;

import com.blas.blascommon.properties.AsyncConfigurationProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@RequiredArgsConstructor
public class BlasAsyncConfiguration implements AsyncConfigurer {

  private static final String THREAD_NAME_PREFIX = "BlasAsyncCore-";

  @Lazy
  private final AsyncConfigurationProperties asyncConfigurationProperties;

  @Bean
  @Override
  public ThreadPoolTaskExecutor getAsyncExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(asyncConfigurationProperties.getCoreSize());
    executor.setMaxPoolSize(asyncConfigurationProperties.getMaxSize());
    executor.setQueueCapacity(asyncConfigurationProperties.getQueueCapacity());
    executor.setThreadNamePrefix(THREAD_NAME_PREFIX);
    executor.setTaskDecorator(new WrappedMDCContextTaskDecorator(true));
    executor.initialize();
    return executor;
  }
}
