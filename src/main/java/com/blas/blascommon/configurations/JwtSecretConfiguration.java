package com.blas.blascommon.configurations;

import static com.blas.blascommon.constants.BlasConstant.BLAS_JWT_SECRET_KEY_VALUE;
import static com.blas.blascommon.security.SecurityUtils.aesDecrypt;
import static com.blas.blascommon.security.SecurityUtils.getPrivateKeyAesFromCertificate;
import static java.util.Objects.requireNonNull;

import com.blas.blascommon.core.service.BlasConfigService;
import com.blas.blascommon.properties.BlasPrivateKeyConfiguration;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class JwtSecretConfiguration {

  private final BlasConfigService blasConfigService;

  private final CertPasswordConfiguration certPasswordConfiguration;

  private final BlasPrivateKeyConfiguration blasPrivateKeyConfiguration;

  public String getJwtSecretKey()
      throws UnrecoverableKeyException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, InvalidKeyException {
    log.info("Extracting secret key...");
    final String jwtSecretKeyEncrypted = blasConfigService.getConfigValueFromKey(
        BLAS_JWT_SECRET_KEY_VALUE);
    return aesDecrypt(requireNonNull(
        getPrivateKeyAesFromCertificate(blasPrivateKeyConfiguration.getCertificate(),
            blasPrivateKeyConfiguration.getAliasBlasPrivateKey(),
            certPasswordConfiguration.getCertPassword())), jwtSecretKeyEncrypted);
  }
}
