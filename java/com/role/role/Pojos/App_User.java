package com.role.role.Pojos;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

@Data
public class App_User implements  UserDetails{
    private  String  User_Id;
    private  String  Name;
    private  String  email;
    private String  password;
    private  Integer  Phone_Number;
    private Set<Role>  set;  

    
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        return  set.stream()
                .map(role ->  new SimpleGrantedAuthority("Role_"+role.name()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return  password;
    }

    @Override
    public String getUsername() {
        // TODO Auto-generated method stub
        return  email;
    }

    
} 