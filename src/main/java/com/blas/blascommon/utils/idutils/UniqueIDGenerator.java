package com.blas.blascommon.utils.idutils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Locale;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class UniqueIDGenerator {

  private static final File URANDOM_FILE = new File("/dev/urandom");
  private static final String WINDOWS = "win";
  private static final String OPERATING_SYSTEM = System.getProperty("os.name")
      .toLowerCase(Locale.US);

  private static boolean isWindows() {
    return OPERATING_SYSTEM.contains(WINDOWS);
  }

  static String getUniqueIdAsString() {
    final byte[] uid = getUniqueIdAsBinary();
    return bytesToBase58(uid);
  }

  private static byte[] getTimeComponent() {
    final long now = System.currentTimeMillis();
    final ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
    buffer.putLong(now);
    return buffer.array();
  }

  private static byte[] getRandomComponent() {
    final byte[] bytes = new byte[11];
    synchronized (bytes) {
      if (isWindows()) {
        final SecureRandom random = new SecureRandom();
        random.nextBytes(bytes);
      } else {
        try {
          try (DataInputStream inputStream = new DataInputStream(
              new FileInputStream(URANDOM_FILE))) {
            inputStream.readFully(bytes);
            return bytes;
          }
        } catch (IOException error) {
          throw new SecurityException("Failed to read from " + URANDOM_FILE, error);
        }
      }
    }
    return bytes;
  }

  private static byte[] getUniqueIdAsBinary() {
    final byte[] transactionId = new byte[16];
    try {
      final byte[] timeComponent = getTimeComponent();
      final byte[] randomComponent = getRandomComponent();
      System.arraycopy(timeComponent, 3, transactionId, 0, 5);
      System.arraycopy(randomComponent, 0, transactionId, 5, 11);
    } catch (Exception error) {
      log.error("Error @ getUniqueIdAsBinary", error);
    }
    return transactionId;
  }

  private static String bytesToBase58(byte[] bytes) {
    return Base58.encode(bytes);
  }
}
