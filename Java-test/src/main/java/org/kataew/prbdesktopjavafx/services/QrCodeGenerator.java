package org.kataew.prbdesktopjavafx.services;




import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;

import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;

import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.NoArgsConstructor;

import org.springframework.stereotype.Service;


import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import java.awt.image.*;


@NoArgsConstructor
@Service
public class QrCodeGenerator {

    public byte[] generateQrCodeSimple(int width, int height,String text)  {

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = null;
        try {
            matrix = writer.encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        } catch (WriterException e) {
            System.out.println("Не удалось записать изображение");
        }

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int grayValue = matrix.get(x, y) ? 0 : 255;
                image.setRGB(x, y, (grayValue << 16) | (grayValue << 8) | grayValue);
            }
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            System.out.println("Не удалось записать изображение");
        }
        return baos.toByteArray();


    }

}
