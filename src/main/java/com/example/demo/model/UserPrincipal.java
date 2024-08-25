package com.example.demo.model;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.io.Serial;
import java.util.Collection;
import java.util.Collections;


/**
 * The UserPrincipal class implements the UserDetails interface and represents the principal (user) in the system.
 * It provides methods to retrieve the user's authorities, password, and username.

 * Usage:
 *   1. Create an instance of the Users class, which represents the user in the system.
 *   2. Create a UserPrincipal object, passing the Users object as a constructor parameter.
 *   3. Use the methods provided by the UserDetails interface to retrieve the user's authorities, password, and username.

 * Dependencies:
 *   - UserDetails: The UserPrincipal class implements this interface to provide user details.
 *   - Users: The UserPrincipal class requires an instance of the Users class to retrieve user information.
 *   - GrantedAuthority: The UserPrincipal class returns a collection of GrantedAuthority objects representing the user's authorities.
 *   - SimpleGrantedAuthority: The UserPrincipal class uses this implementation of the GrantedAuthority interface to create a singleton collection of authorities.

 * Additional Information:
 *   - The UserPrincipal class is annotated with the @RequiredArgsConstructor annotation, indicating that a constructor is generated which accepts the final Users object as a parameter
 * .
 *   - The UserPrincipal class implements the UserDetails interface, which is part of the Spring Security framework and provides user details necessary for authentication and authorization
 * .
 *   - The serialVersionUID field is used for versioning purposes during serialization and deserialization.
 *   - The getAuthorities() method returns a collection of GrantedAuthority objects representing the user's authorities. In this implementation, a singleton collection containing
 *  a single SimpleGrantedAuthority with the role "USER" is returned.
 *   - The getPassword() method returns the user's password, retrieved from the Users object passed to the constructor.
 *   - The getUsername() method returns the user's email address, retrieved from the Users object passed to the constructor.
 */
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    private final Users user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {

        return user.getPassword();
    }

    @Override
    public String getUsername() {

        return user.getEmail();
    }
}
