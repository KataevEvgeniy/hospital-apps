package org.kataew.prbdesktopjavafx.services;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;


@Service
public class Printer {


    public void saveAsPdf(byte[] image, String outputPath)  {
        Document document = new Document();
        try {
        FileOutputStream fos = new FileOutputStream(outputPath);

            PdfWriter.getInstance(document, fos);


        document.open();
        Image pdfImage = Image.getInstance(image);
        document.add(pdfImage);
        document.close();

        fos.close();
        } catch (DocumentException | IOException e) {
            System.out.println("Не удалось сохранить в pdf");
        }
    }

}
