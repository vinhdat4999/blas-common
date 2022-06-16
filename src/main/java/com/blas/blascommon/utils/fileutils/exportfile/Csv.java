package com.blas.blascommon.utils.fileutils.exportfile;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Csv {

    public static void exportToCsv(String[] headers, List<String[]> data, String path) {
        List<String[]> list = new ArrayList<>();
        list.add(headers);
        list.addAll(data);
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            writer.writeAll(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
