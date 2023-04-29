package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.BlasConstant.BLAS_CERT_PASSWORD;
import static com.blas.blascommon.constants.BlasConstant.BLAS_JWT_SECRET_KEY_VALUE;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.security.SecurityUtils.base64Decode;
import static com.blas.blascommon.security.SecurityUtils.getPrivateKeyAesFromCertificate;

import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.properties.BlasPrivateKeyConfiguration;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Objects;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecretKeyConfiguration {

  @Autowired
  private BlasConfigService blasConfigService;

  @Bean
  public String getCertPassword() {
    return new String(base64Decode(blasConfigService.getConfigValueFromKey(BLAS_CERT_PASSWORD)));
  }

  @Bean
  public String getJwtSecretKey(BlasPrivateKeyConfiguration blasPrivateKeyConfiguration)
      throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeyException {
    final String jwtSecretKeyEncrypted = blasConfigService.getConfigValueFromKey(
        BLAS_JWT_SECRET_KEY_VALUE);
    return aesDecrypt(Objects.requireNonNull(
            getPrivateKeyAesFromCertificate(blasPrivateKeyConfiguration.getCertificate(),
                blasPrivateKeyConfiguration.getAliasBlasPrivateKey(), getCertPassword())),
        jwtSecretKeyEncrypted);
  }
}
