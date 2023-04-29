/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import services.UserService;

/**
 *
 * @author yasmine
 */
public class Pdf {
    public void GeneratePdf(String filename, User f, int id) throws FileNotFoundException, DocumentException, BadElementException, IOException, InterruptedException, SQLException {

        Document document = new Document() {
        };
        PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
        document.open();

        UserService sf = new UserService();
        document.add(new Paragraph("            Date  :"+LocalDateTime.now()));
        document.add(new Paragraph("            User :"+f.getNom()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------"));

        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Email :" + f.getEmail()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Date naissance:" + f.getDate_naissance()));
        document.add(new Paragraph("                      "));
        document.add(new Paragraph("Role :" + f.getRoles()));
        document.add(new Paragraph("                      "));

       

        document.add(new Paragraph("---------------------------------------------------------------------------------------------------------------------------------- "));
        document.close();
        Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
    }
}
