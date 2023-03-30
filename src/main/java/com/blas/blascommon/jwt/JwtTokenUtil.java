package com.blas.blascommon.jwt;

import static com.blas.blascommon.constants.SecurityConstant.AUTHORIZATION;
import static com.blas.blascommon.constants.SecurityConstant.BEARER_SPACE;
import static com.blas.blascommon.enums.Role.SYSTEM;
import static java.lang.System.currentTimeMillis;

import com.blas.blascommon.properties.JwtConfigurationProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenUtil {

  @Lazy
  @Autowired
  private JwtConfigurationProperties jwtConfigurationProperties;

  // for retrieving any information from token we will need the secret key
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser().setSigningKey(jwtConfigurationProperties.getSecret()).parseClaimsJws(token)
        .getBody();
  }

  public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = getAllClaimsFromToken(token);
    return claimsResolver.apply(claims);
  }

  // retrieve username from jwt token
  public String getUsernameFromToken(String token) {
    return getClaimFromToken(token, Claims::getSubject);
  }

  // retrieve expiration date from jwt token
  public Date getExpirationDateFromToken(String token) {
    return getClaimFromToken(token, Claims::getExpiration);
  }// check if the token has expired

  private Boolean isTokenExpired(String token) {
    final Date expirationDate = getExpirationDateFromToken(token);
    return expirationDate.before(new Date());
  }

  // generate token for user
  public String generateToken(UserDetails userDetails) {
    Map<String, Object> claims = new HashMap<>();
    return doGenerateToken(claims, userDetails.getUsername(),
        jwtConfigurationProperties.getTimeToExpired());
  }

  // generate token for internal system
  public Map<String, String> generateInternalSystemToken() {
    Map<String, Object> claims = new HashMap<>();
    return Map.of(AUTHORIZATION, BEARER_SPACE + doGenerateToken(claims, SYSTEM.toString(),
        jwtConfigurationProperties.getTimeToExpiredInternalToken()));
  }

  // while creating the token -
  // 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
  // 2. Sign the JWT using the HS512 algorithm and secret key.
  // 3. According to JWS Compact
  private String doGenerateToken(Map<String, Object> claims, String subject, long timeToExpired) {
    return Jwts.builder().setClaims(claims).setSubject(subject)
        .setIssuedAt(new Date(currentTimeMillis()))
        .setExpiration(new Date(currentTimeMillis() + timeToExpired * 1000))
        .signWith(SignatureAlgorithm.HS512, jwtConfigurationProperties.getSecret()).compact();
  }

  // validate token
  public Boolean validateToken(String token, UserDetails userDetails) {
    final String username = getUsernameFromToken(token);
    return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
}
