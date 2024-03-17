package com.blas.blascommon.jwt;

import static com.blas.blascommon.constants.SecurityConstant.HMAC_SHA_512;
import static java.lang.System.currentTimeMillis;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.configurations.JwtSecretConfiguration;
import com.blas.blascommon.properties.JwtConfigurationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenUtil {

  @Lazy
  private final JwtConfigurationProperties jwtConfigurationProperties;

  private final JwtSecretConfiguration jwtSecretConfiguration;

  // for retrieving any information from token we will need the secret key
  private Claims getAllClaimsFromToken(String token)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    return Jwts.parser()
        .verifyWith(
            new SecretKeySpec(jwtSecretConfiguration.getJwtSecretKey().getBytes(), HMAC_SHA_512))
        .build()
        .parseSignedClaims(token)
        .getPayload();
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  // retrieve username from jwt token
  public String getUsernameFromToken(String token)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    return getClaimFromToken(token, Claims::getSubject);
  }

  // retrieve expiration date from jwt token
  public Date getExpirationDateFromToken(String token)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    return getClaimFromToken(token, Claims::getExpiration);
  }// check if the token has expired

  private Boolean isTokenExpired(String token)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    final Date expirationDate = getExpirationDateFromToken(token);
    return expirationDate.before(new Date());
  }

  // generate token for user
  public String generateToken(String username)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, username, jwtConfigurationProperties.getTimeToExpired());
  }

  // while creating the token -
  // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
  // 2. Sign the JWT using the HS512 algorithm and secret key.
  // 3. According to JWS Compact
  private String doGenerateToken(Map<String, Object> claims, String subject, long timeToExpired)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    return Jwts.builder()
        .claims(claims)
        .subject(subject)
        .issuedAt(new Date(currentTimeMillis()))
        .expiration(new Date(currentTimeMillis() + timeToExpired * 1000))
        .signWith(new SecretKeySpec(jwtSecretConfiguration.getJwtSecretKey().getBytes(UTF_8),
            HMAC_SHA_512))
        .compact();
  }

  // validate token
  public Boolean validateToken(String token, UserDetails userDetails)
      throws InvalidAlgorithmParameterException, UnrecoverableKeyException, IllegalBlockSizeException, NoSuchPaddingException, CertificateException, KeyStoreException, IOException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
