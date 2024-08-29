package com.example.demo.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
    public void generatePdf(@RequestParam("url") String url){
            log.info("got url: {}",url);
//        https://chatgpt.com/share/0f0effaa-3edd-47be-bb1b-28fb1b6eafc0
        return ;
    }

}
