package com.role.role.Pojos;
//we will  defin  about th e  db 

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Driver extends  App_User{
    private  String  Car_Number;
    private  LocalDate  Date_Joined;
    private  List<String>  Documents;
    private  String  Car_Type;
    

}
