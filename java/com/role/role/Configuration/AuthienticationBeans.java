package com.role.role.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.role.role.Service.UserServiceImplementaion;

@Configuration
public class AuthienticationBeans {
    @Autowired
    private  UserServiceImplementaion userServiceImplementaion;
    @Bean
    public  AuthenticationManager  authenticationManager(AuthenticationConfiguration authenticationConfiguration ) throws Exception {
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean 
    public AuthenticationProvider  daoauthenticationProvider() {
        DaoAuthenticationProvider  daoAuthenticationProvider  =  new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userServiceImplementaion);
        return  daoAuthenticationProvider;
    }

    @Bean
    public  PasswordEncoder passwordEncoder() {
        return  new  BCryptPasswordEncoder(12);
    }
}
