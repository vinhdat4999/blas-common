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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.SecretKeyEntry;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class SecurityUtils {

  public static String getUsernameLoggedIn() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal instanceof UserDetails userDetails ? userDetails.getUsername()
        : principal.toString();
  }

  public static String getUserIdLoggedIn(AuthUserService authUserService) {
    return authUserService.getAuthUserByUsername(getUsernameLoggedIn()).getUserId();
  }

  public static String md5Encode(String rawPassword) {
    return getHashedString(rawPassword, MD5);
  }

  public static String sha1Encode(String rawPassword) {
    String hashedPassword = EMPTY;
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
        .map(Enum::toString).toList();
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

  public static String aesEncrypt(String privateKey, String value)
      throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
    Key key = new SecretKeySpec(privateKey.getBytes(), "AES");
    SecureRandom random = new SecureRandom();
    byte[] iv = new byte[16];
    random.nextBytes(iv);
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
    byte[] ciphertext = cipher.doFinal(value.getBytes());
    byte[] encrypted = new byte[iv.length + ciphertext.length];
    System.arraycopy(iv, 0, encrypted, 0, iv.length);
    System.arraycopy(ciphertext, 0, encrypted, iv.length, ciphertext.length);
    return Base64.getEncoder().encodeToString(encrypted);
  }

  public static String aesDecrypt(String privateKey, String encryptedValue)
      throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
    Key key = new SecretKeySpec(privateKey.getBytes(), "AES");
    byte[] encrypted = Base64.getDecoder().decode(encryptedValue);
    byte[] iv = Arrays.copyOfRange(encrypted, 0, 16);
    byte[] ciphertext = Arrays.copyOfRange(encrypted, 16, encrypted.length);
    IvParameterSpec ivSpec = new IvParameterSpec(iv);
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
    byte[] plaintext = cipher.doFinal(ciphertext);
    return new String(plaintext);
  }

  /**
   * Create certificate
   *
   * @param outputFilePath Path of output certificate (.p12)
   * @param keyDataMap     Map store data, key is alias, value is key value
   * @param password       Password of certificate
   */
  private static void createAesCertificate(String outputFilePath, Map<String, String> keyDataMap,
      String password)
      throws KeyStoreException, CertificateException, IOException, NoSuchAlgorithmException {
    KeyStore keyStore = KeyStore.getInstance("PKCS12");
    keyStore.load(null, null);
    for (Map.Entry<String, String> entryKey : keyDataMap.entrySet()) {
      String alias = entryKey.getKey();
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(256);
      SecretKey key = new SecretKeySpec(entryKey.getValue().getBytes(), "AES");
      Entry entry = new SecretKeyEntry(key);
      keyStore.setEntry(alias, entry, new KeyStore.PasswordProtection(password.toCharArray()));
    }
    FileOutputStream outputStream = new FileOutputStream(outputFilePath);
    keyStore.store(outputStream, password.toCharArray());
    outputStream.close();
  }

  public static String getPrivateKeyAesFromCertificate(String certificatePath, String alias,
      String password) throws Exception {
    KeyStore keyStore = KeyStore.getInstance("PKCS12");
    FileInputStream inputStream = new FileInputStream(certificatePath);
    keyStore.load(inputStream, password.toCharArray());
    inputStream.close();
    Key key = keyStore.getKey(alias, password.toCharArray());
    if (key == null) {
      return null;
    }
    return new String(key.getEncoded());
  }
}
