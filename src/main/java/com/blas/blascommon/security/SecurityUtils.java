package com.blas.blascommon.security;

import com.blas.blascommon.core.service.AuthUserService;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Formatter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {

  private SecurityUtils() {
  }

  public static String getUsernameLoggedIn() {
    String username = "";
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
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    if (md == null) {
      return "";
    }
    byte[] messageDigest = md.digest(rawPassword.getBytes());
    BigInteger no = new BigInteger(1, messageDigest);
    StringBuilder hashText = new StringBuilder(no.toString(16));
    while (hashText.length() < 32) {
      hashText.append("0").append(hashText);
    }
    return hashText.toString();
  }

  public static String sha1Encode(String rawPassword) {
    String hashedPassword = "";
    try {
      MessageDigest crypt = MessageDigest.getInstance("SHA-1");
      crypt.reset();
      crypt.update(rawPassword.getBytes(StandardCharsets.UTF_8));
      hashedPassword = byteToHex(crypt.digest());
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return hashedPassword;
  }

  public static String sha256Encode(String rawPassword) {
    MessageDigest digest = null;
    byte[] hash = new byte[0];
    try {
      digest = MessageDigest.getInstance("SHA-256");
      hash = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
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
    MessageDigest md = null;
    try {
      md = MessageDigest.getInstance("SHA-512");
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    if (md == null) {
      return "";
    }
    byte[] messageDigest = md.digest(rawPassword.getBytes());
    BigInteger no = new BigInteger(1, messageDigest);
    StringBuilder hashtext = new StringBuilder(no.toString(16));
    while (hashtext.length() < 32) {
      hashtext.append("0").append(hashtext);
    }
    return hashtext.toString();
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
      MessageDigest md = MessageDigest.getInstance("SHA-512");
      md.update(salt);
      byte[] bytes = md.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < bytes.length; i++) {
        sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
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
}
