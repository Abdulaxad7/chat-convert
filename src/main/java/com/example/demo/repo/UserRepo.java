package com.example.demo.repo;

import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends MongoRepository<Users,Integer> {
        Users findUsersByEmail(String email);
}
