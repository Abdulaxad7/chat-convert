package com.example.demo.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.concurrent.ThreadLocalRandom;

@Data
@Document(collection = "Entrepreneur")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TypeAlias("User")
public class Users {
    @Id
    private int Id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public void setId(int Id) {
        Id = ThreadLocalRandom.current().nextInt(10000000, 99999999);
        this.Id = Id;
    }
}
