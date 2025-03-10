package com.blas.blascommon.utils.fileutils.importfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Csv {

  public static List<String[]> importFromCsv(String path) throws IOException {
    List<String[]> data = new ArrayList<>();
    Path pathToFile = Paths.get(path);
    try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
      String line = br.readLine();
      while (line != null) {
        String[] attributes = line.split(",");
        data.add(attributes);
        line = br.readLine();
      }
    }
    return data;
  }
}
