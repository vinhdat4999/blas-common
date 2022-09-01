package com.blas.blascommon.utils.fileutils;

import static com.blas.blascommon.constants.Configuration.BAR_CODE_HEIGHT;
import static com.blas.blascommon.constants.Configuration.BAR_CODE_WIDTH;
import static com.blas.blascommon.constants.Configuration.QR_HEIGHT;
import static com.blas.blascommon.constants.Configuration.QR_WIDTH;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.UPCAWriter;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ImageUtils {

  public static BufferedImage resizeImage(byte[] image, int targetWidth,
      int targetHeight) {
    try {
      InputStream is = new ByteArrayInputStream(image);
      BufferedImage originalImage = ImageIO.read(is);
      Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight,
          Image.SCALE_AREA_AVERAGING);
      BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight,
          BufferedImage.TYPE_INT_RGB);
      outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
      return outputImage;
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public static BufferedImage genQrCode(String content) {
    try {
      QRCodeWriter qrCodeWriter = new QRCodeWriter();
      BitMatrix matrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, QR_WIDTH,
          QR_HEIGHT);
      return MatrixToImageWriter.toBufferedImage(matrix);
    } catch (WriterException e) {
      throw new RuntimeException(e);
    }
  }

  public static BufferedImage genBarCode(String content) {
    UPCAWriter upcaWriter = new UPCAWriter();
    BitMatrix matrix = upcaWriter.encode(content, BarcodeFormat.UPC_A, BAR_CODE_WIDTH,
        BAR_CODE_HEIGHT);
    return MatrixToImageWriter.toBufferedImage(matrix);
  }
}