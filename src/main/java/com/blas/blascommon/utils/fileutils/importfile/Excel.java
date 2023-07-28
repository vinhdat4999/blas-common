package com.blas.blascommon.utils.fileutils.importfile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.experimental.UtilityClass;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@UtilityClass
public class Excel {

  public static List<String[]> importFromExcel(InputStream inputStream, String fileName)
      throws IOException {
    List<String[]> data = new ArrayList<>();
    try (inputStream) {
      Workbook workbook = getWorkbook(inputStream, fileName);
      Sheet sheet = workbook.getSheetAt(0);
      for (Row nextRow : sheet) {
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
    }
    return data;
  }

  private static Workbook getWorkbook(InputStream inputStream, String fileName)
      throws IOException {
    Workbook workbook;
    if (fileName.endsWith("xlsx")) {
      workbook = new XSSFWorkbook(inputStream);
    } else if (fileName.endsWith("xls")) {
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
      case _NONE, BLANK, ERROR:
      default:
        break;
    }
    return cellValue;
  }
}
