package com.example.demo.verif;

import com.example.demo.model.Users;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.UUID;

import static com.example.demo.verif.Message.HTML_MESSAGE;
import static com.example.demo.verif.Message.PASSWORD;


/**
 * The EmailVerification class is a service class that is responsible for sending email verification codes to users and validating the codes.
 * It uses the JavaMailSender and SimpleMailMessage classes from the Spring Framework to send emails.

 * Usage:
 *   1. Instantiate the EmailVerification class.
 *      Example:
 *      ```
 *      EmailVerification verification = new EmailVerification();
 *      ```

 *   2. Call the SendEmail method to send an email verification code to a user.
 *      Example:
 *      ```
 *      verification.SendEmail(user);
 *      ```

 *   3. Call the CheckEmail method to check if a provided verification code is valid.
 *      Example:
 *      ```
 *      boolean valid = verification.CheckEmail(code);
 *      ```

 * Dependencies:
 *   - JavaMailSender: Used for sending emails. An instance of this class needs to be injected into the EmailVerification class using the @Autowired annotation.
 *   - SimpleMailMessage: Used for creating email message objects. An instance of this class is created in the constructor of the EmailVerification class.

 * Additional Information:
 *   - The EmailVerification class is annotated with the @Service annotation, indicating that it is a service component that should be automatically detected and instantiated by Spring
 * .
 *   - The class has a private String pass field, which stores the verification code.
 *   - The class has a constructor that initializes the message field with a new instance of the SimpleMailMessage class.
 *   - The class has an Async annotation on the SendEmail method, which allows the method to be executed asynchronously. This can be useful for sending emails in the background without
 *  blocking the main thread.
 *   - The SendEmail method generates a random verification code using the UUID class, sets up the email message using the MimeMessageHelper class, and sends the email using the java
 * MailSender instance.
 *   - The CheckEmail method checks if the provided verification code is equal to the code stored in the pass field, and returns a boolean indicating the result.
 */
@Service
@Data
public class EmailVerification {
    @Autowired
    private JavaMailSender javaMailSender;

    private SimpleMailMessage message;

    public EmailVerification() {
        this.message = new SimpleMailMessage();
    }

    @Async
    public void SendEmail(Users user) {
        PASSWORD = UUID.randomUUID().toString();
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
            messageHelper.setTo(user.getEmail());
            messageHelper.setSubject("Email Verification");
            messageHelper.setText(HTML_MESSAGE(), true);
            System.out.println(PASSWORD);
            messageHelper.setFrom("Chat Creator <noreply@chat.com>");
        };
        javaMailSender.send(messagePreparator);
    }
    public boolean CheckEmail(String password){
        return PASSWORD.equals(password);
    }
}