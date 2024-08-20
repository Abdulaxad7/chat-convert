package com.example.demo.controller;

import com.example.demo.model.Users;
import com.example.demo.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService service;


    @GetMapping("/register")
    public String register_get(){

        return "register";
    }

    @GetMapping("/authenticate")
    public String authenticate_get(){
        return  "login";
    }

    @GetMapping("/")
    public String home(){
        return "home";
    }


    @PostMapping("/register")
    public String register(@RequestBody Users user) {

        System.out.println(service.register(user));
        return "home";
    }

    @PostMapping("/authenticate")
    public String authenticate(@RequestBody Users user){

        System.out.println(service.authenticate(user));
        return "home";
    }

    @PostMapping( "/login")
    public String login(@RequestBody Users user){

        System.out.println(service.authenticate(user));
        return "home";
    }
}

