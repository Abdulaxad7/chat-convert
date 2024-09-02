package com.example.demo.service;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URI;
import java.net.URL;

@Service
public class LoadPDF {

    private static final Logger log = LoggerFactory.getLogger(LoadPDF.class);

    public static void generatePdf(String urlString) {
        BufferedReader in = null;
        FileWriter writer = null;

        try {
            URL url = new URI(urlString).toURL();
            in = new BufferedReader(new InputStreamReader(url.openStream()));
            File inputFile = new File("/Users/abdulaxad/eclipse-workspace/Chat/src/main/java/com/example/demo/htmlSource/example.html");
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
            PdfWriter.getInstance(document1,new FileOutputStream("/Users/abdulaxad/eclipse-workspace/Chat/src/main/java/com/example/demo/htmlSource/output.pdf"));
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
        } finally {
            try {
                if (in != null) in.close();
                if (writer != null) writer.close();
            } catch (Exception ioException) {
                log.error(ioException.toString());
            }
        }
    }
}
