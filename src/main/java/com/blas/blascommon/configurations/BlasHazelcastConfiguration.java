package com.blas.blascommon.configurations;

import com.blas.blascommon.properties.HazelcastConfiguration;
import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Slf4j
@Configuration
@EnableCaching
@RequiredArgsConstructor
@Profile({"aks", "local"})
public class BlasHazelcastConfiguration implements CachingConfigurer {

  private static final String LOCAL_BLAS_HAZELCAST_INSTANCE = "blasHazelcastInstance";

  private final HazelcastConfiguration hazelcastConfiguration;

  @Bean
  @Profile("local")
  public Config hazelcastLocalConfig() {
    log.info("Hazelcast localhost configuration. Instance name: {}", LOCAL_BLAS_HAZELCAST_INSTANCE);
    Config config = new Config();
    config.setInstanceName(LOCAL_BLAS_HAZELCAST_INSTANCE);
    return config;
  }

  @Bean
  @Profile("aks")
  public Config hazelcastConfig() {
    Config config = new Config();
    config.getNetworkConfig().getJoin().getTcpIpConfig().setEnabled(false);
    config.getNetworkConfig().getJoin().getMulticastConfig().setEnabled(false);
    String hazelcastService = hazelcastConfiguration.getHazelcastService();
    String aksNamespace = hazelcastConfiguration.getAksNamespace();
    config.getNetworkConfig().getJoin().getKubernetesConfig().setEnabled(true)
        .setProperty("namespace", aksNamespace)
        .setProperty("service-name", hazelcastService);
    log.info("Kubernetes Hazelcast configuration - Namespace: {} - Hazelcast Service name: {}",
        aksNamespace, hazelcastService);
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
