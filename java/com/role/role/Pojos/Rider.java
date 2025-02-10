package com.role.role.Pojos;
import java.time.LocalDate;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Rider extends  App_User{
        private  LocalDate date_joined;
}
