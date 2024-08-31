package com.example.demo.service;

import com.example.demo.verif.EmailVerification;
import com.example.demo.auth.LoginRequest;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import static com.example.demo.filter.JwtFilter.CHECK;


/**
 * The AuthenticationService class is responsible for handling user registration and authentication functionality.
 * It provides methods to register a new user and authenticate a user.

 * Dependencies:
 *   - UserRepo: Used for accessing the user repository to save user data.
 *   - AuthenticationManager: Used for performing user authentication.
 *   - JwtServices: Used for generating authentication tokens.

 * Usage:
 *   AuthenticationService authService = new AuthenticationService(userRepo, authManager, jwtService);
 *   String token = authService.register(user);
 *   String authenticatedToken = authService.authenticate(loginRequest);
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {
    private final EmailVerification verification;

    private final UserRepo repo;

    private final AuthenticationManager authManager;

    private final JwtServices jwtService;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();

    public void register(Users user, HttpServletResponse response){
        user.setId(0);
        user.setPassword(encoder.encode(user.getPassword()));
        getCookie(response, user.getEmail());

        repo.save(user);
    }

    public void authenticate(LoginRequest user, HttpServletResponse response) {

        Authentication manager = authManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        if (manager.isAuthenticated()) {
            getCookie(response, user.getEmail());
        }
    }

    private void getCookie(HttpServletResponse response, String email) {
        CHECK=true;
        String token = jwtService.generateToken(email);
        Cookie jwtCookie = new Cookie("jwt", token);
        jwtCookie.setHttpOnly(false);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(jwtCookie);
    }

    public boolean verifyMail( String code) {
        return verification.CheckEmail(code);
    }


    public void sendMail(Users user) {
        verification.SendEmail(user);
    }
}
