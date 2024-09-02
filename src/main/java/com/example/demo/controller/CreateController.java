package com.example.demo.controller;

import com.example.demo.service.LoadPDF;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class CreateController {
    @GetMapping("/home")
    public String home(){
        return "home";
    }
    @GetMapping("/")
    public String home2(){
        return "home";
    }
    @PostMapping("/generate/pdf")
    public void generatePdf(@RequestParam("url") String url, HttpServletResponse response) {
        log.info("got url: {}", url);
        LoadPDF.generatePdf(url,response);
        LoadPDF.Download(response);
     }

}
