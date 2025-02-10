package com.role.role.Dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignupDto {
    @NotEmpty
    private  String Name;
    
    @Email
    private  String  Email;
    @NotNull
    private  Integer Phone;
  
    @NotEmpty
    private String  password;

    @NotNull
    private  String  Role;



}
