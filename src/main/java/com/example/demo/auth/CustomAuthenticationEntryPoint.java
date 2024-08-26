package com.example.demo.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * CustomAuthenticationEntryPoint is a class that implements the AuthenticationEntryPoint interface,
 * which provides a centralized location for handling authentication failure.
 * When an unauthenticated request is made, this class redirects the user to the login page.
 * This class is annotated with @Component to enable Spring component scanning and automatic bean creation.

 * This class overrides the commence() method from the AuthenticationEntryPoint interface,
 * which is called when authentication fails.
 * The commence() method takes in the HttpServletRequest, HttpServletResponse, and AuthenticationException as parameters.
 * When called, it redirects the user to the login page by calling response.sendRedirect("/login").

 * Example usage:
 *      CustomAuthenticationEntryPoint entryPoint = new CustomAuthenticationEntryPoint();

 * If you want to use this class as a Spring bean and leverage its automatic creation and dependency injection,
 * annotate it with @Component or any other Spring stereotype annotation.
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        response.sendRedirect("/api/auth/login");
    }

}
