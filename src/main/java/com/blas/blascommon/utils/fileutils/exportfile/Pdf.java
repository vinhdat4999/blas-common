package com.blas.blascommon.utils.fileutils.exportfile;


import static com.itextpdf.html2pdf.HtmlConverter.convertToPdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Pdf {

  /**
   * Export a list of paragraph to file PDF
   *
   * @param paragraphList List of string, a string is a paragraph
   * @param path          Path of output PDF file
   * @throws IOException
   * @throws DocumentException
   */
  public static void exportToPdf(List<String> paragraphList, String path)
      throws IOException, DocumentException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(path));
    document.open();
    BaseFont unicode = BaseFont.createFont("font/arial.ttf", BaseFont.IDENTITY_H,
        BaseFont.EMBEDDED);
    Font font = new Font(unicode, 12);
    for (String paragraph : paragraphList) {
      document.add(new Paragraph(paragraph, font));
    }
    document.close();
  }

  /**
   * Export HTML to file PDF
   *
   * @param htmlContent String of HTML content
   * @param path        Path of output PDF file
   * @throws IOException
   */
  public static void exportHtmlToPdf(String htmlContent, String path) throws IOException {
    OutputStream outputStream = new FileOutputStream(path);
    convertToPdf(htmlContent, outputStream);
    outputStream.close();
  }
}
