package com.blas.blascommon.configurations;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class BlasHazelcastConfiguration implements CachingConfigurer {

  @Bean
  public Config hazelcastConfig() {
    Config config = new Config();
    config.setInstanceName("blasHazelcastInstance");
    return config;
  }

  @Bean
  public HazelcastInstance hazelcastInstance(Config hazelcastConfig) {
    return Hazelcast.newHazelcastInstance(hazelcastConfig);
  }

  @Bean
  public CacheManager cacheManager(HazelcastInstance hazelcastInstance) {
    return new HazelcastCacheManager(hazelcastInstance);
  }
}
