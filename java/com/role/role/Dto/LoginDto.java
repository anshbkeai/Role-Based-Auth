package com.role.role.Dto;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
@Data
public class LoginDto {
    
    @jakarta.validation.constraints.Email
    private  String  Email;
    @NotEmpty
    private  String  password;
    @NotEmpty
    private  String Role;


}
