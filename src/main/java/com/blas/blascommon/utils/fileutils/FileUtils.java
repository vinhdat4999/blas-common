package com.blas.blascommon.utils.fileutils;

import static com.blas.blascommon.utils.StringUtils.DOT;
import static com.blas.blascommon.utils.StringUtils.SLASH;
import static org.apache.commons.lang3.StringUtils.contains;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.substring;

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
import java.nio.file.Paths;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.InputStreamResource;

@UtilityClass
public class FileUtils {

  @Deprecated(since = "4.3.0")
  public static boolean createBlankFile(String path) throws IOException {
    boolean createFileSucceed;
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

  public static String convertByteToAppropriateType(long type) {
    String[] types = {"B", "KB", "MB", "GB", "TB"};
    int count = 0;
    float temp = type;
    while (temp >= 1024) {
      count++;
      temp /= 1024.0F;
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

  public static void zipFolder(String sourceFolderPath, String outputZipFilePath)
      throws IOException {
    FileOutputStream fos = new FileOutputStream(outputZipFilePath);
    ZipOutputStream zipOut = new ZipOutputStream(fos);

    File sourceFolder = new File(sourceFolderPath);
    zipFile(sourceFolder, sourceFolder.getName(), zipOut);

    zipOut.close();
    fos.close();
  }

  private static void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut)
      throws IOException {
    if (fileToZip.isHidden()) {
      return;
    }

    if (fileToZip.isDirectory()) {
      if (fileName.endsWith(SLASH)) {
        zipOut.putNextEntry(new ZipEntry(fileName));
        zipOut.closeEntry();
      } else {
        zipOut.putNextEntry(new ZipEntry(fileName + SLASH));
        zipOut.closeEntry();
      }

      File[] children = fileToZip.listFiles();
      assert children != null;
      for (File childFile : children) {
        zipFile(childFile, fileName + SLASH + childFile.getName(), zipOut);
      }
      return;
    }

    try (FileInputStream fis = new FileInputStream(fileToZip)) {
      ZipEntry zipEntry = new ZipEntry(fileName);
      zipOut.putNextEntry(zipEntry);

      byte[] buffer = new byte[1024];
      int length;
      while ((length = fis.read(buffer)) >= 0) {
        zipOut.write(buffer, 0, length);
      }
    }
  }

  public static boolean isValidFileName(String fileName) {
    if (checkValidationFileOrFolder(fileName)) {
      return false;
    }
    String[] reservedNames = {"CON", "PRN", "AUX", "NUL", "COM", "LPT"};
    String baseName =
        contains(fileName, DOT) ? substring(fileName, 0, fileName.lastIndexOf('.')) : fileName;
    for (String reserved : reservedNames) {
      if (baseName.equalsIgnoreCase(reserved)) {
        return false;
      }
    }
    return true;
  }

  public static boolean isValidFolderName(String folderName) {
    if (checkValidationFileOrFolder(folderName)) {
      return false;
    }
    return !folderName.contains(SLASH) && !folderName.contains("\\");
  }

  private static boolean checkValidationFileOrFolder(String folderName) {
    if (isBlank(folderName)) {
      return true;
    }
    if (folderName.length() > 255) {
      return true;
    }
    String invalidChars = "\\:*?\"<>|";
    return folderName.chars().anyMatch(c -> invalidChars.indexOf(c) != -1);
  }
}
