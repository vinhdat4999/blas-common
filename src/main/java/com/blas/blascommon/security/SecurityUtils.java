package com.blas.blascommon.security;

import static com.blas.blascommon.constants.SecurityConstant.MD5;
import static com.blas.blascommon.constants.SecurityConstant.SHA1;
import static com.blas.blascommon.constants.SecurityConstant.SHA256;
import static com.blas.blascommon.constants.SecurityConstant.SHA512;
import static com.blas.blascommon.enums.Role.ADMIN;
import static com.blas.blascommon.enums.Role.BOD;
import static com.blas.blascommon.enums.Role.MAINTAINER;
import static com.blas.blascommon.enums.Role.MANAGER;
import static com.blas.blascommon.enums.Role.SYSTEM;
import static com.blas.blascommon.utils.StringUtils.EMPTY;
import static com.blas.blascommon.utils.StringUtils.UNDERSCORE;
import static java.nio.charset.StandardCharsets.UTF_8;

import com.blas.blascommon.core.service.AuthUserService;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtils {

  public static String getUsernameLoggedIn() {
    String username;
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    return username;
  }

  public static String getUserIdLoggedIn(AuthUserService authUserService) {
    String username = getUsernameLoggedIn();
    return authUserService.getAuthUserByUsername(username).getUserId();
  }

  public static String md5Encode(String rawPassword) {
    return getHashedString(rawPassword, MD5);
  }

  public static String sha1Encode(String rawPassword) {
    String hashedPassword = "";
    try {
      MessageDigest crypt = MessageDigest.getInstance(SHA1);
      crypt.reset();
      crypt.update(rawPassword.getBytes(UTF_8));
      hashedPassword = byteToHex(crypt.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hashedPassword;
  }

  public static String sha256Encode(String rawPassword) {
    MessageDigest digest;
    byte[] hash = new byte[0];
    try {
      digest = MessageDigest.getInstance(SHA256);
      hash = digest.digest(rawPassword.getBytes(UTF_8));
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    final StringBuilder hexString = new StringBuilder();
    for (int i = 0; i < hash.length; i++) {
      final String hex = Integer.toHexString(0xff & hash[i]);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  public static String sha512Encode(String rawPassword) {
    return getHashedString(rawPassword, SHA512);
  }

  public static byte[] getSalt() {
    byte[] salt = new byte[16];
    try {
      SecureRandom random = new SecureRandom();
      random.nextBytes(salt);
    } catch (Exception exception) {
      exception.printStackTrace();
    }
    return salt;
  }

  public static String sha512Encode(String rawPassword, byte[] salt) {

    String generatedPassword = null;
    try {
      MessageDigest md = MessageDigest.getInstance(SHA512);
      md.update(salt);
      byte[] bytes = md.digest(rawPassword.getBytes(UTF_8));
      StringBuilder sb = new StringBuilder();
      for (byte aByte : bytes) {
        sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
      }
      generatedPassword = sb.toString();
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return generatedPassword;
  }

  private static String byteToHex(final byte[] hash) {
    Formatter formatter = new Formatter();
    for (byte b : hash) {
      formatter.format("%02x", b);
    }
    String result = formatter.toString();
    formatter.close();
    return result;
  }

  public static String base64Encode(byte[] content) {
    return Base64.getEncoder().encodeToString(content);
  }

  public static byte[] base64Decode(String base64Content) {
    return Base64.getDecoder().decode(base64Content);
  }

  public boolean isPrioritizedRole(Authentication authentication) {
    List<String> prioritizedRole = Stream.of(SYSTEM, ADMIN, BOD, MAINTAINER, MANAGER)
        .map(Enum::toString).collect(Collectors.toList());
    return prioritizedRole.contains(
        authentication.getAuthorities().iterator().next().toString().split(UNDERSCORE)[1]);
  }

  private static String getHashedString(String rawPassword, String hashType) {
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance(hashType);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    if (md == null) {
      return EMPTY;
    }
    byte[] messageDigest = md.digest(rawPassword.getBytes());
    BigInteger no = new BigInteger(1, messageDigest);
    StringBuilder hashText = new StringBuilder(no.toString(16));
    while (hashText.length() < 32) {
      hashText.append("0").append(hashText);
    }
    return hashText.toString();
  }
}
