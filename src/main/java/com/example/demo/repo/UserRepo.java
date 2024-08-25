package com.example.demo.repo;

import com.example.demo.model.Users;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * The UserRepo interface is a repository interface that extends the MongoRepository interface.
 * It provides methods to interact with the database and perform CRUD operations on the Users collection.

 * Usage:
 *   1. Define an instance of the UserRepo interface and inject it into the desired class.
 *      Example:
 *      ```
 *      @Autowired
 *      private UserRepo userRepo;
 *      ```

 *   2. Use the inherited methods from the MongoRepository interface to perform database operations.
 *      Example:
 *      ```
 *      Users user = userRepo.findUsersByEmail(email);
 *      ```

 * Dependencies:
 *   - MongoRepository: The UserRepo interface extends this interface to inherit its methods for interacting with the MongoDB database.

 * Additional Information:
 *   - The UserRepo interface is annotated with the @Repository annotation, indicating that it is a repository component that should be automatically detected and instantiated by
 *  Spring.
 *   - The interface specifies a method called findUsersByEmail that returns a Users object based on the provided email.
 *   - The Users class is the entity class that represents a user in the system. It is annotated with various annotations such as @Data, @Document, @AllArgsConstructor, etc., to define
 *  its properties and behavior.
 *   - The UserRepo interface is typically used by service classes (e.g., UserServices) to perform data access operations related to users.
 */
@Repository
public interface UserRepo extends MongoRepository<Users,Integer> {
        Users findUsersByEmail(String email);
}
