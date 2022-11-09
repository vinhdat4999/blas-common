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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.InputStreamResource;

@UtilityClass
public class FileUtils {

  public static boolean createBlankFile(String path) {
    boolean createFileSucceed = false;
    File newFile = new File(path);
    try {
      createFileSucceed = newFile.createNewFile();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return createFileSucceed;
  }

  public static String readFile(String path) {
    StringBuilder content = new StringBuilder("");
    String tempStr;
    try (BufferedReader objReader = new BufferedReader(new FileReader(path));) {
      while ((tempStr = objReader.readLine()) != null) {
        content.append(tempStr).append("\n");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return content.toString();
  }

  public static byte[] getByteArray(String path) {
    try {
      return Files.readAllBytes(Paths.get(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new byte[0];
  }

  public static InputStream getInputStream(String path) {
    try {
      return new FileInputStream(path);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static InputStreamResource getInputStreamResource(String path) {
    try {
      return new InputStreamResource(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return null;
  }

  public static long getFileSize(String path) {
    return new File(path).length();
  }

  public static byte[] readBytesRange(String filePath, long start, long end) {
    try {
      Path path = Paths.get(filePath);
      byte[] data = Files.readAllBytes(path);
      byte[] result = new byte[(int) (end - start) + 1];
      System.arraycopy(data, (int) start, result, 0, (int) (end - start) + 1);
      return result;
    } catch (IOException e) {
      e.printStackTrace();
    }
    return new byte[0];
  }

  public static void writeBufferImageToFile(BufferedImage image, String formatName, String path) {
    try {
      ImageIO.write(image, formatName, new File(path));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeTextToFile(String content, String path) {
    try (FileWriter writer = new FileWriter(path); BufferedWriter buffer = new BufferedWriter(
        writer);) {
      buffer.write(content);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void writeByteArrayToFile(byte[] content, String path) {
    try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));) {
      bos.write(content);
      bos.flush();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static boolean move(String oldPath, String newPath) {
    File oldFile = new File(oldPath);
    File newFile = new File(newPath);
    boolean moveSuccess = false;
    try {
      moveSuccess = oldFile.renameTo(newFile);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return moveSuccess;
  }

  public static void copy(String oldPath, String newPath) {
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static void delete(String pathStr) {
    try {
      Path path = Paths.get(pathStr);
      Files.delete(path);
    } catch (IOException e) {
      e.printStackTrace();
    }
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
}
