package com.role.role.Filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.role.role.Pojos.App_User;
import com.role.role.Service.UserServiceImplementaion;
import com.role.role.ServiceImplemetation.JwtServiceImplementation;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtFilter extends  OncePerRequestFilter{

    @Autowired
    private  UserServiceImplementaion  userServiceImplementaion;

    @Autowired
   private  JwtServiceImplementation jwtServiceImplementation; 

    @Override

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // TODO Auto-generated method stub
        String  request_uri  =  request.getRequestURI();
        if(request_uri.startsWith("/app/") ) {
            filterChain.doFilter(request, response);
            return;
        }
        String  token  = "";
        String  user_email = "";
        String  authheader =  request.getHeader("Authorization");
        if(!authheader.isEmpty() &&  authheader.startsWith("Bearer")) {
            logger.info(authheader);

            token  =  authheader.substring(7);
            logger.info("The  Token   is  "+token);
            user_email =  jwtServiceImplementation.getUserId(token);
            logger.info(user_email);
        }
        if(!user_email.isEmpty() &&  SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails app_User   =  userServiceImplementaion.loadUserByUsername(user_email);

                List<GrantedAuthority> authorities = app_User.getAuthorities().stream()
            .map(auth -> new SimpleGrantedAuthority("ROLE_" + auth.getAuthority().substring(5)))
            .collect(Collectors.toList());

            for(GrantedAuthority  g :  authorities) {
                logger.info(g.getAuthority());
            }

            app_User.getAuthorities().stream()
            .forEach(map -> logger.info(map.getAuthority()));

            if (jwtServiceImplementation.validate(app_User, token)) {
                
                UsernamePasswordAuthenticationToken  token2 =  new UsernamePasswordAuthenticationToken(app_User.getUsername(), null,authorities);
                 token2.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(token2);
    
                    logger.info("Authentication successful for user: " + user_email);
            }
        }

        filterChain.doFilter(request, response);
    }

}
