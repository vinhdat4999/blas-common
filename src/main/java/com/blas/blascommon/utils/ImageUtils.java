package com.blas.blascommon.utils;

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
}
