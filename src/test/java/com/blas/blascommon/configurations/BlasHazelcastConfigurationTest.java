package com.blas.blascommon.configurations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.hazelcast.config.Config;
import com.hazelcast.core.HazelcastInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.CacheManager;

@ExtendWith(MockitoExtension.class)
class BlasHazelcastConfigurationTest {

  private BlasHazelcastConfiguration configuration;

  @Mock
  private Config mockHazelcastConfig;

  @Mock
  private HazelcastInstance mockHazelcastInstance;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    configuration = new BlasHazelcastConfiguration();
  }

  @Test
  void testHazelcastConfig() {
    Config config = configuration.hazelcastConfig();
    assertNotNull(config);
  }

  @Test
  void shouldReturnHazelcastInstanceWhenConfigIsProvided() {
    Config config = new Config();
    HazelcastInstance instance = configuration.hazelcastInstance(config);
    assertNotNull(instance);
  }

  @Test
  void testCacheManager() {
    CacheManager cacheManager = configuration.cacheManager(mockHazelcastInstance);
    assertNotNull(cacheManager);
  }
}
