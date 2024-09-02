package com.example.demo.controller;

import com.example.demo.service.LoadPDF;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
@RequestMapping("/args")
@Slf4j
public class DController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/")
    public String home2(){
        return "home";
    }
    @PostMapping("/generate/pdf")
    public void generatePdf(@RequestParam("url") String url, HttpServletResponse response){
        log.info("got url: {}", url);
        LoadPDF.generatePdf(url);
        File file = new File("/Users/abdulaxad/eclipse-workspace/Chat/src/main/java/com/example/demo/htmlSource/output.pdf");

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
        }
    }


}
