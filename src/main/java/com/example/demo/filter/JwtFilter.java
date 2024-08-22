package com.example.demo.filter;

import com.example.demo.model.Users;
import com.example.demo.service.JwtServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
    private final JwtServices jwtServices;

    private final ApplicationContext context;
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response, @NonNull FilterChain filterChain)  throws ServletException, IOException {

        String authHeader=request.getHeader("Authorization");
        String token= null;
        String email=null;

        if (authHeader!=null && authHeader.startsWith("Bearer")){
            token=authHeader.substring(7);
            email=jwtServices.extractEmail(token);
        }
        if (email!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            UserDetails details=context.getBean(UserDetailsService.class).loadUserByUsername(email);

            if (jwtServices.validateToken(token,details)){
                UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(details,null ,details.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }


        filterChain.doFilter(request,response);
    }
}
