package com.example.demo.controller;


import com.example.demo.auth.LoginRequest;
import com.example.demo.model.Users;
import com.example.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
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
        System.out.println("got home");

        return "home";
    }


    @PostMapping("/register")
    public String register( Users user) {

        System.out.println(service.register(user));
        return "home";
    }

//    @PostMapping("/login")
//    public String authenticate( LoginRequest request){
//        System.out.println("got login post");
//        System.out.println(service.authenticate(request));
//        return "home";
//    }
@PostMapping("/login")
public ResponseEntity<?> authenticate( LoginRequest request) {
    String token = service.authenticate(request);
    if (token.equals("fail")) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", "Invalid credentials"));
    }
    return ResponseEntity.ok(Map.of("token", token));
}

    @GetMapping("/protected-endpoint")
    public ResponseEntity<?> getProtectedData(HttpServletRequest request) {
        // Example protected data
        Map<String, String> data = Map.of("message", "This is protected data");
        return ResponseEntity.ok(data);
    }


}

