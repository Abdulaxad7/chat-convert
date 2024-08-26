package com.example.demo.auth;

import com.example.demo.model.Users;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.UUID;

/**
 * The EmailVerification class is responsible for sending email verification codes and checking if a given code is correct.
 * It utilizes the Spring Framework's*/
@Service
@Data
public class EmailVerification {
    private String pass;

    @Autowired
    private JavaMailSender sendmail;

    private SimpleMailMessage message;

    public EmailVerification() {this.message = new SimpleMailMessage();}

    @Async
    public void SendEmail(Users user){
        pass = UUID.randomUUID().toString();
        message.setTo(user.getEmail());
        message.setSubject("Email Verification");
        message.setText("Your Email Verification Code is:  \n" + pass + "\n" + "Do not share this code with anyone");
        System.out.println(pass);
        sendmail.send(message);
    }

    public boolean CheckEmail(String password){
        return pass.equals(password);
    }
}