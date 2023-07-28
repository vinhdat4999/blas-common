package com.blas.blascommon.utils.fileutils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.InputStreamResource;

@UtilityClass
public class FileUtils {

  public static boolean createBlankFile(String path) throws IOException {
    boolean createFileSucceed = false;
    File newFile = new File(path);
    createFileSucceed = newFile.createNewFile();
    return createFileSucceed;
  }

  public static String readFile(String path) throws IOException {
    StringBuilder content = new StringBuilder();
    String tempStr;
    try (BufferedReader objReader = new BufferedReader(new FileReader(path));) {
      while ((tempStr = objReader.readLine()) != null) {
        content.append(tempStr).append("\n");
      }
    }
    return content.toString();
  }

  public static byte[] getByteArray(String path) throws IOException {
    return Files.readAllBytes(Paths.get(path));
  }

  public static InputStream getInputStream(String path) throws FileNotFoundException {
    return new FileInputStream(path);
  }

  public static InputStreamResource getInputStreamResource(String path)
      throws FileNotFoundException {
    return new InputStreamResource(new FileInputStream(path));
  }

  public static long getFileSize(String path) {
    return new File(path).length();
  }

  public static byte[] readBytesRange(String filePath, long start, long end) throws IOException {
    byte[] data = Files.readAllBytes(Paths.get(filePath));
    byte[] result = new byte[(int) (end - start) + 1];
    System.arraycopy(data, (int) start, result, 0, (int) (end - start) + 1);
    return result;
  }

  public static void writeBufferImageToFile(BufferedImage image, String formatName, String path)
      throws IOException {
    ImageIO.write(image, formatName, new File(path));
  }

  public static void writeTextToFile(String content, String path) throws IOException {
    try (FileWriter writer = new FileWriter(path); BufferedWriter buffer = new BufferedWriter(
        writer)) {
      buffer.write(content);
    }
  }

  public static void writeByteArrayToFile(byte[] content, String path) throws IOException {
    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));) {
      bos.write(content);
      bos.flush();
    }
  }

  public static boolean move(String oldPath, String newPath) {
    File oldFile = new File(oldPath);
    File newFile = new File(newPath);
    return oldFile.renameTo(newFile);
  }

  public static void copy(String oldPath, String newPath) throws IOException {
    try (BufferedReader br = new BufferedReader(
        new FileReader(oldPath)); BufferedWriter bw = new BufferedWriter(
        new FileWriter(newPath));) {
      int i;
      do {
        i = br.read();
        if (i != -1) {
          if (Character.isLowerCase((char) i)) {
            bw.write(Character.toUpperCase((char) i));
          } else if (Character.isUpperCase((char) i)) {
            bw.write(Character.toLowerCase((char) i));
          } else {
            bw.write((char) i);
          }
        }
      } while (i != -1);
    }
  }

  public static void delete(String pathStr) throws IOException {
    Path path = Paths.get(pathStr);
    Files.delete(path);
  }

  public static String convertByteToAppropriateType(long type) {
    String[] types = {"B", "KB", "MB", "GB", "TB"};
    int count = 0;
    float temp = type;
    while (temp >= 1024) {
      count++;
      temp /= 1024.0;
    }
    return String.format("%.2f", temp) + " " + types[count];
  }

  public static String convertInputStreamToString(InputStream inputStream) throws IOException {
    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    StringBuilder stringBuilder = new StringBuilder();
    String line;
    while ((line = reader.readLine()) != null) {
      stringBuilder.append(line);
    }
    return stringBuilder.toString();
  }
}
