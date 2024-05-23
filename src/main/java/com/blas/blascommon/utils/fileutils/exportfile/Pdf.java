package com.blas.blascommon.utils.fileutils.exportfile;


import static com.blas.blascommon.utils.datetimeutils.DateTimeUtils.DATE_DDMMYYYY_SLASH_FORMAT;
import static com.itextpdf.html2pdf.HtmlConverter.convertToPdf;
import static com.itextpdf.kernel.colors.DeviceRgb.RED;
import static org.apache.commons.lang3.StringUtils.upperCase;

import com.blas.blascommon.utils.TemplateUtils;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.element.Image;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.OptionalInt;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Pdf {

  /**
   * Export a list of paragraph to file PDF
   *
   * @param paragraphList List of string, a string is a paragraph
   * @param path          Path of output PDF file
   * @param size          Font size, pass null if use default font size
   * @throws IOException
   * @throws DocumentException
   */
  public static void exportToPdf(List<String> paragraphList, String path, Integer size)
      throws IOException, DocumentException {
    Document document = new Document();
    PdfWriter.getInstance(document, new FileOutputStream(path));
    document.open();
    BaseFont unicode = BaseFont.createFont("font/segoeui.ttf", BaseFont.IDENTITY_H,
        BaseFont.EMBEDDED);
    Font font = new Font(unicode, OptionalInt.of(size).orElse(12));
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

  public static void addPasswordToPdfFile(InputStream fileInputStream, String outputFile,
      String password) throws IOException, DocumentException {
    PdfReader reader = new PdfReader(fileInputStream);
    FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
    PdfStamper stamper = new PdfStamper(reader, fileOutputStream);
    stamper.setEncryption(password.getBytes(), password.getBytes(), PdfWriter.ALLOW_PRINTING,
        PdfWriter.ENCRYPTION_AES_256);
    stamper.close();
    fileOutputStream.close();
    reader.close();
  }

  public static void addSignature(String inputFilePath, String outputFilePath, String signedBy)
      throws IOException {
    signedBy = "SIGN BY: " + upperCase(signedBy);
    String signDate =
        "SIGN DATE: " + new SimpleDateFormat(DATE_DDMMYYYY_SLASH_FORMAT).format(new Date());
    try (com.itextpdf.kernel.pdf.PdfReader reader = new com.itextpdf.kernel.pdf.PdfReader(
        inputFilePath); com.itextpdf.kernel.pdf.PdfWriter writer = new com.itextpdf.kernel.pdf.PdfWriter(
        outputFilePath); PdfDocument pdfDoc = new PdfDocument(reader,
        writer); com.itextpdf.layout.Document document = new com.itextpdf.layout.Document(pdfDoc)) {

      int numPages = pdfDoc.getNumberOfPages();
      PdfFont font = PdfFontFactory.createFont();

      for (int pageNum = 1; pageNum <= numPages; pageNum++) {
        PdfPage page = pdfDoc.getPage(pageNum);
        Rectangle pageSize = page.getPageSize();
        PdfCanvas pdfCanvas = new PdfCanvas(page);

        pdfCanvas.setFontAndSize(font, 10);

        float borderWidth = 25;
        float textWidth = font.getWidth(signDate, 10);
        float textHeight = 12;

        pdfCanvas.setLineWidth(1f);
        pdfCanvas.setStrokeColor(RED);
        pdfCanvas.rectangle(pageSize.getWidth() - textWidth - borderWidth - 60,
            textHeight + borderWidth, textWidth + borderWidth + 30,
            2 * textHeight + 2 * borderWidth);
        pdfCanvas.stroke();

        float imageWidth = 20;
        float imageHeight = 20;

        ClassLoader classLoader = TemplateUtils.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("images/checkmark.png");
        assert inputStream != null;
        Image checkMarkImage = new Image(
            ImageDataFactory.create(inputStream.readAllBytes())).scaleToFit(imageWidth,
            imageHeight);

        document.add(
            checkMarkImage.setFixedPosition(pageSize.getWidth() - textWidth - borderWidth - 45,
                textHeight + borderWidth + imageHeight + 15));

        pdfCanvas.setColor(RED, true);
        addWatermark(pdfCanvas, pageSize, textWidth, borderWidth,
            textHeight + borderWidth + imageHeight + 15, signedBy);
        addWatermark(pdfCanvas, pageSize, textWidth, borderWidth, borderWidth + imageHeight + 15,
            signDate);
      }
    }
  }

  private static void addWatermark(PdfCanvas pdfCanvas, Rectangle pageSize, float textWidth,
      float borderWidth, float borderWidth1, String signDate) {
    pdfCanvas.beginText().moveText(pageSize.getWidth() - textWidth - borderWidth - 30, borderWidth1)
        .showText(signDate).endText();
  }
}
