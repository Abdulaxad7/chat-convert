package com.example.demo.controller;


import com.example.demo.auth.LoginRequest;
import com.example.demo.model.Users;
import com.example.demo.service.AuthenticationService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationService service;


    @GetMapping("/register")
    public String register_get(){
        System.out.println("got register");
        return "register";
    }

    @GetMapping("/login")
    public String authenticate_get(){

        return  "login";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }

    @PostMapping("/register")
    public String register( Users user, HttpServletResponse response) {
       service.register(user);
        return "home";
    }

    @PostMapping("/login")
    public String authenticate(LoginRequest loginRequest) throws IOException {
        service.authenticate(loginRequest);
        return "home";
    }



}

