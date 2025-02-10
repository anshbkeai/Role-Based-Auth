package com.role.role.ServiceImplemetation;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.role.role.Pojos.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtServiceImplementation {

    private  SecretKey secretKey;
    public JwtServiceImplementation() {
        SecretKey_Genrate();
    }
    private  void  SecretKey_Genrate() {
        KeyGenerator keyGenerator;
        try {
            keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            secretKey  =  keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            // hey  thos is  the  dev  Erroe  yu  hvae  to  handle  thos  
            e.printStackTrace();
        }
       
    }

    public  String  Genrate_JWT(String  user_email,  String  user_name,  Set<Role>  role,String  user_id) {
            Map<String,Object>  map  =  new  HashMap<>();
            return  Jwts.builder()
                    .issuedAt(new  Date(System.currentTimeMillis()))
                    .subject(user_email)
                    .claim("user_name", user_name)
                    .claim("Role", role)
                    .claim("user_id", user_id)
                    .expiration(new  Date(System.currentTimeMillis()*36000))
                    .claims(map)
                    .signWith(secretKey).compact();
            
    }
    public String  getUserId(String token) {
        final  Claims  claims  =  Jwts
                                        .parser()
                                        .verifyWith(secretKey)
                                        .build()
                                        .parseSignedClaims(token)
                                        .getPayload();
        log.info(claims.getSubject()+"   "+ claims.getExpiration());
        return claims.getSubject();
    }

    public boolean validate(UserDetails userDetails, String token) {
        // TODO Auto-generated method stub
        String  token_userId  = getUserId(token);
        return  ((userDetails.getUsername().equals(token_userId)) &&   !isTokenExpired(token));  
    }
    private  boolean  isTokenExpired(String  token) {
        final  Claims  claims  =  Jwts
                                    .parser()
                                    .verifyWith(secretKey)
                                    .build()
                                    .parseSignedClaims(token)
                                    .getPayload();
        log.info(claims.getSubject()+"   "+ claims.getExpiration());
        return  claims.getExpiration().before(new  Date());

    }

}
