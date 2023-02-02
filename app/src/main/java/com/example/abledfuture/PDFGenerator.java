package com.example.abledfuture;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;

public class PDFGenerator {
    public static void generatePDF(String text, File fileName) {
        try {
            Document document = new Document();
            FileOutputStream fOut = new FileOutputStream(fileName);
            PdfWriter.getInstance(document, fOut);
            document.open();
            document.add(new Paragraph(text));
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
