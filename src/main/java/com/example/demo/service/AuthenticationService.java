package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;




@Component
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo repo;

    private final AuthenticationManager authManager;

    private final JwtServices jwtService;

    private final BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(15);

    public String register(Users user){
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return jwtService.generateToken(user.getEmail());
    }

    public String authenticate(Users user) {

        Authentication manager=authManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
        if (manager.isAuthenticated()){
            return jwtService.generateToken(user.getEmail());
        }
        return "fail";
    }
}
