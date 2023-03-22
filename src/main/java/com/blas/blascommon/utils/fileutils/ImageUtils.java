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
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import lombok.experimental.UtilityClass;
import net.coobird.thumbnailator.Thumbnails;

@UtilityClass
public class ImageUtils {

  public static BufferedImage compressImage(String path, float scale) throws IOException {
    BufferedImage bufferedImage = getBufferImage(path);
    if (bufferedImage == null) {
      return null;
    }
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    Thumbnails.of(bufferedImage).outputFormat("JPG")
        .size(bufferedImage.getWidth(), bufferedImage.getHeight()).outputQuality(scale)
        .toOutputStream(outputStream);
    byte[] data = outputStream.toByteArray();
    ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
    return ImageIO.read(inputStream);
  }

  public static BufferedImage resizeImage(byte[] image, int targetWidth, int targetHeight)
      throws IOException {
    InputStream is = new ByteArrayInputStream(image);
    BufferedImage originalImage;
    originalImage = ImageIO.read(is);
    if (originalImage == null) {
      return null;
    }
    Image resultingImage = originalImage.getScaledInstance(targetWidth, targetHeight,
        Image.SCALE_AREA_AVERAGING);
    BufferedImage outputImage = new BufferedImage(targetWidth, targetHeight,
        BufferedImage.TYPE_INT_RGB);
    outputImage.getGraphics().drawImage(resultingImage, 0, 0, null);
    return outputImage;
  }

  public static BufferedImage getBufferImage(String path) throws IOException {
    return ImageIO.read(new File(path));
  }

  public static BufferedImage genQrCode(String content) throws WriterException {
    QRCodeWriter qrCodeWriter = new QRCodeWriter();
    BitMatrix matrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT);
    return MatrixToImageWriter.toBufferedImage(matrix);
  }

  public static BufferedImage genBarCode(String content) {
    UPCAWriter upcaWriter = new UPCAWriter();
    BitMatrix matrix = upcaWriter.encode(content, BarcodeFormat.UPC_A, BAR_CODE_WIDTH,
        BAR_CODE_HEIGHT);
    return MatrixToImageWriter.toBufferedImage(matrix);
  }
}
