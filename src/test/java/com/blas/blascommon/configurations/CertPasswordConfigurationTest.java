package com.blas.blascommon.configurations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.blas.blascommon.core.service.BlasConfigService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CertPasswordConfigurationTest {

  private CertPasswordConfiguration configuration;
  private BlasConfigService mockBlasConfigService;

  @BeforeEach
  void setUp() {
    mockBlasConfigService = mock(BlasConfigService.class);
    configuration = new CertPasswordConfiguration(mockBlasConfigService);
  }

  @Test
  void testGetCertPassword() {
    String expectedPassword = "password123";
    String encodedPassword = "cGFzc3dvcmQxMjM=";
    when(mockBlasConfigService.getConfigValueFromKey("BLAS_CERT_PASSWORD")).thenReturn(
        encodedPassword);
    String actualPassword = configuration.getCertPassword();
    assertEquals(expectedPassword, actualPassword);
  }
}
