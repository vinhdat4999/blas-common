package com.blas.blascommon.utils.fileutils.exportfile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@UtilityClass
public class Excel {

  public static void exportToExcel(String[] headers, List<String[]> data, String path)
      throws IOException {
    Workbook workbook = getWorkbook(path);
    short format = (short) BuiltinFormats.getBuiltinFormat("#,##0");
    CellStyle cellStyleFormatNumber = workbook.createCellStyle();
    cellStyleFormatNumber.setDataFormat(format);
    Sheet sheet = workbook.createSheet("Sheet1");
    writeHeader(sheet, 0, headers);
    int rowIndex = 1;
    for (String[] dataLine : data) {
      Row row = sheet.createRow(rowIndex);
      writeDataLine(dataLine, row, cellStyleFormatNumber);
      rowIndex++;
    }
    autosizeColumn(sheet, headers.length);
    createOutputFile(workbook, path);
  }

  private static Workbook getWorkbook(String excelFilePath) {
    Workbook workbook = null;
    if (excelFilePath.endsWith("xlsx")) {
      workbook = new XSSFWorkbook();
    } else if (excelFilePath.endsWith("xls")) {
      workbook = new HSSFWorkbook();
    } else {
      throw new IllegalArgumentException("The specified file is not Excel file");
    }
    return workbook;
  }

  private static void writeHeader(Sheet sheet, int rowIndex, String[] headers) {
    CellStyle cellStyle = createStyleForHeader(sheet);
    Row row = sheet.createRow(rowIndex);
    for (int index = 0; index < headers.length; index++) {
      Cell cell = row.createCell(index);
      cell.setCellStyle(cellStyle);
      cell.setCellValue(headers[index]);
    }
  }

  private static void writeDataLine(String[] dataLine, Row row, CellStyle cellStyleFormatNumber) {
    for (int index = 0; index < dataLine.length; index++) {
      Cell cell = row.createCell(index);
      cell.setCellStyle(cellStyleFormatNumber);
      cell.setCellValue(dataLine[index]);
    }
  }

  private static CellStyle createStyleForHeader(Sheet sheet) {
    // Create font
    Font font = sheet.getWorkbook().createFont();
    font.setFontName("Times New Roman");
    font.setBold(true);
    font.setFontHeightInPoints((short) 14);
    font.setColor(IndexedColors.WHITE.getIndex());

    // Create CellStyle
    CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
    cellStyle.setFont(font);
    cellStyle.setFillForegroundColor(IndexedColors.BLUE.getIndex());
    cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    cellStyle.setBorderBottom(BorderStyle.THIN);
    return cellStyle;
  }

  private static void autosizeColumn(Sheet sheet, int lastColumn) {
    for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
      sheet.autoSizeColumn(columnIndex);
    }
  }

  private static void createOutputFile(Workbook workbook, String excelFilePath)
      throws IOException {
    try (OutputStream os = new FileOutputStream(excelFilePath)) {
      workbook.write(os);
    }
  }
}
