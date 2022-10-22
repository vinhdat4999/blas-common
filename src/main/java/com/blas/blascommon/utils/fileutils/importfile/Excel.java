package com.blas.blascommon.utils.fileutils.importfile;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {

  private Excel() {
  }

  public static List<String[]> importFromExcel(String excelFilePath) {
    List<String[]> data = new ArrayList<>();
    try (InputStream inputStream = new FileInputStream(excelFilePath)) {
      Workbook workbook = getWorkbook(inputStream, excelFilePath);
      Sheet sheet = workbook.getSheetAt(0);
      Iterator<Row> iterator = sheet.iterator();
      while (iterator.hasNext()) {
        Row nextRow = iterator.next();
        Iterator<Cell> cellIterator = nextRow.cellIterator();
        List<String> dataLineList = new ArrayList<>();
        while (cellIterator.hasNext()) {
          Cell cell = cellIterator.next();
          Object cellValue = getCellValue(cell);
          if (cellValue == null || cellValue.toString().isEmpty()) {
            continue;
          }
          dataLineList.add(cellValue.toString());
        }
        String[] dataLine = new String[dataLineList.size()];
        data.add(dataLineList.toArray(dataLine));
      }
      workbook.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return data;
  }

  private static Workbook getWorkbook(InputStream inputStream, String excelFilePath)
      throws IOException {
    Workbook workbook = null;
    if (excelFilePath.endsWith("xlsx")) {
      workbook = new XSSFWorkbook(inputStream);
    } else if (excelFilePath.endsWith("xls")) {
      workbook = new HSSFWorkbook(inputStream);
    } else {
      throw new IllegalArgumentException("The specified file is not Excel file");
    }
    return workbook;
  }

  private static Object getCellValue(Cell cell) {
    CellType cellType = cell.getCellType();
    Object cellValue = null;
    switch (cellType) {
      case BOOLEAN:
        cellValue = cell.getBooleanCellValue();
        break;
      case FORMULA:
        Workbook workbook = cell.getSheet().getWorkbook();
        FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
        cellValue = evaluator.evaluate(cell).getNumberValue();
        break;
      case NUMERIC:
        cellValue = cell.getNumericCellValue();
        break;
      case STRING:
        cellValue = cell.getStringCellValue();
        break;
      case _NONE:
      case BLANK:
      case ERROR:
      default:
        break;
    }
    return cellValue;
  }
}