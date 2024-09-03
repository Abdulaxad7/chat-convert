package com.example.demo.service;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URI;
import java.net.URL;




/**
 * Class responsible for loading and creating PDF files from given URL content.
 */
public class LoadPDF {

    private static final Logger log = LoggerFactory.getLogger(LoadPDF.class);

    @SneakyThrows
    public static void generatePdf(String urlString, HttpServletResponse response) {
        BufferedReader in = null;
        FileWriter writer = null;
        File inputFile = new File("/******/******/******/******/src/main/java/com/example/demo/htmlSource/example.html");
        log.info("HTML created successfully.");
        try {
            URL url = new URI(urlString).toURL();
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            writer = new FileWriter(inputFile);

            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                writer.write(inputLine);
            }
            writer.close();
            in.close();

            Document document = Jsoup.parse(inputFile, "UTF-8");
            Elements scriptTags = document.select("script");

            com.itextpdf.text.Document  document1=new com.itextpdf.text.Document();
            PdfWriter.getInstance(document1,new FileOutputStream("/******/******/******/******/src/main/java/com/example/demo/htmlSource/output.pdf"));
            document1.open();
            StringBuilder string= new StringBuilder();
            for (var scriptTag : scriptTags) {
                string.append(scriptTag);
            }
            Paragraph paragraph=new Paragraph(string.toString());

            document1.add(paragraph);
            document1.close();
           log.info("PDF created successfully.");

        } catch (Exception e) {
            log.error(e.toString());
            response.sendRedirect("/");
        } finally {
            if (inputFile.delete()) {
                log.info("HTML File removed");
            } else {
                log.error("Exception on removing HTML file");
            }
            try {
                if (in != null) in.close();
                if (writer != null) writer.close();
            } catch (Exception ioException) {
                log.error(ioException.toString());
            }
        }
    }
    public static void Download( HttpServletResponse response){
        String path="/******/******/******/******/src/main/java/com/example/demo/htmlSource/output.pdf";
        File file = new File(path);

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=output.pdf");

        try (FileInputStream fis = new FileInputStream(file);
             ServletOutputStream os = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.flush();
        } catch (IOException e) {
            log.error(e.toString());
        }finally {
            if (file.delete()) {
                log.info("PDF File removed");
            } else {
                log.error("Exception on removing PDF file");
            }
        }
    }
}
