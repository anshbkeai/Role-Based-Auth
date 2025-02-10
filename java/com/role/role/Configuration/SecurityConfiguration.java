package com.role.role.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.role.role.Filter.JwtFilter;

@Component
@EnableWebSecurity
public class SecurityConfiguration {

    // @Autowired 
    // private  AuthienticationBeans  authienticationBeans;

    @Autowired
    private  JwtFilter jwtFilter;  

    @Bean
    public  SecurityFilterChain   securityFilterChain(HttpSecurity httpSecurity  ) throws Exception {
        httpSecurity
                    .csrf(csrf -> csrf.disable())
                 
                    .authorizeHttpRequests( authorizeHttpRequests -> {
                        authorizeHttpRequests.requestMatchers("/","/app/**").permitAll();
                        authorizeHttpRequests.requestMatchers("/rider/**").hasRole("Rider");
                        authorizeHttpRequests.anyRequest().authenticated();
                    })
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return httpSecurity.build();
    }
}
