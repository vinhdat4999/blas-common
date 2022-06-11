package com.blas.blascommon.utils;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.imageio.ImageIO;

public class FileUtils {

    public static boolean createBlankFile(String path) {
        boolean createFileSucceed = false;
        try {
            File newFile = new File(path);
            createFileSucceed = newFile.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return createFileSucceed;
    }

    public static String readFile(String path) {
        BufferedReader objReader = null;
        String content = "";
        try {
            String tempStr = "";
            objReader = new BufferedReader(new FileReader(path));
            while ((tempStr = objReader.readLine()) != null) {
                content += tempStr + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (objReader != null) {
                    objReader.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return content;
    }

    public static byte[] getByteArray(String path) {
        try {
            return Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeBufferImageToFile(BufferedImage image, String formatName, String path) {
        try {
            ImageIO.write(image, formatName, new File(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeTextToFile(String content, String path) {
        try {
            FileWriter writer = new FileWriter(path);
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(content);
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeByteArrayToFile(byte[] content, String path) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
            bos.write(content);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean rename(String oldPath, String newPath) {
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
        try {
            BufferedReader br = new BufferedReader(new FileReader(oldPath));
            BufferedWriter bw = new BufferedWriter(new FileWriter(newPath));
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
            br.close();
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean delete(String path) {
        File file = new File(path);
        boolean deletedFile = file.delete();
        return deletedFile;
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
