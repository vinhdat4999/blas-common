package com.blas.blascommon.security;

import static com.blas.blascommon.exceptions.BlasErrorCodeEnum.MSG_BLAS_APP_FAILURE;
import static com.blas.blascommon.security.SecurityUtils.getPrivateKeyAesFromCertificate;

import com.blas.blascommon.configurations.CertPasswordConfiguration;
import com.blas.blascommon.exceptions.types.BadRequestException;
import com.blas.blascommon.properties.BlasPrivateKeyConfiguration;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KeyService {

  private final CertPasswordConfiguration certPasswordConfiguration;

  @Lazy
  private final BlasPrivateKeyConfiguration blasPrivateKeyConfiguration;

  public String getBlasPrivateKey() {
    try {
      return getPrivateKeyAesFromCertificate(blasPrivateKeyConfiguration.getCertificate(),
          blasPrivateKeyConfiguration.getAliasBlasPrivateKey(),
          certPasswordConfiguration.getCertPassword());
    } catch (KeyStoreException | CertificateException | IOException | NoSuchAlgorithmException |
             UnrecoverableKeyException exception) {
      throw new BadRequestException(MSG_BLAS_APP_FAILURE);
    }
  }
}
