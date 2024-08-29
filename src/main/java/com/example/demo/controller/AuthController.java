package com.example.demo.controller;


import com.example.demo.auth.LoginRequest;
import com.example.demo.model.Users;
import com.example.demo.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;



@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationService service;
    private  Users user;
    @GetMapping("/login")
    public String authenticate_get(){

        return  "login";
    }
    @PostMapping("/login")
    public void authenticate(LoginRequest loginRequest,HttpServletResponse response) throws IOException {
        service.authenticate(loginRequest,response);
        response.sendRedirect("/");
    }

    @GetMapping("/register")
    public String register_get(){
        System.out.println("got register");
        return "register";

    }
    @PostMapping("/register")
    public void register( Users user, HttpServletResponse response) throws IOException {
        this.user=user;
        response.sendRedirect("email-ver");

    }

    @GetMapping("/email-ver")
    public String verification(Model model){
        model.addAttribute("email",user.getEmail());
        service.sendMail(user);
        return "verification";
    }
    @PostMapping("/email-ver")
    public void verification(@RequestParam("code")String code,HttpServletResponse response) throws IOException {
        if (service.verifyMail(code)){
            service.register(user,response);
            response.sendRedirect("/");
        }else response.sendRedirect("email-ver");
    }
}
