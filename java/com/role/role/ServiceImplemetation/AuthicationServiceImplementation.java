package com.role.role.ServiceImplemetation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.role.role.Dto.LoginDto;
import com.role.role.Pojos.Driver;
import com.role.role.Pojos.Rider;
import com.role.role.Repositry.DriverRepo;
import com.role.role.Repositry.RiderRepo;

@Service
public class AuthicationServiceImplementation {
    @Autowired
    private  RiderRepo riderRepo;

    @Autowired
    private DriverRepo  driverRepo;

    @Autowired
    private  AuthenticationManager authenticationManager;

    @Autowired
    private  JwtServiceImplementation  jwtServiceImplementation;

    public  Map<String,  String> customAuth(LoginDto loginDto) {
        Map<String,String>  map  =  new  HashMap<>();
        if(loginDto.getRole().equals("Rider")) {
            Rider  rider   =  riderRepo.findByEmail(loginDto.getEmail()).get();
            Authentication authentication  =  authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(rider.getEmail(), loginDto.getPassword(), rider.getAuthorities()));
            if(authentication.isAuthenticated()) {
                // this  part  is just  ofr  the teest
               String  jwttoken  =  jwtServiceImplementation.Genrate_JWT(rider.getEmail(), rider.getName(), rider.getSet(), rider.getUser_Id());
                map.put("Rider_id",rider.getUser_Id());
                map.put("Rider_name", rider.getName());
                map.put("jwttoken", jwttoken);
                return  map;

            }
            map.put("Rider_Id", "Failed");
            map.put("message ","Hey  invalid  Cremdtiols  ");
        }
        Authentication authentication  =  authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
        if(authentication.isAuthenticated()) {
            // this  part  is just  ofr  the teest
            Driver  rider   =  driverRepo.findByEmail(loginDto.getEmail()).get();
            map.put("Driver_id",rider.getUser_Id());
            map.put("Driver_name", rider.getName());
            return  map;

        }
        map.put("Driver_Id", "Failed");
        map.put("message ","Hey  invalid  Cremdtiols  ");
        return map;
        /*         Map<String, String> responseMap = new HashMap<>();

        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword())
            );

            if (authentication.isAuthenticated()) {
                if ("Rider".equalsIgnoreCase(loginDto.getRole())) {
                    Optional<Rider> riderOptional = riderRepo.findByEmail(loginDto.getEmail());
                    if (riderOptional.isPresent()) {
                        Rider rider = riderOptional.get();
                        responseMap.put("UserType", "Rider");
                        responseMap.put("Rider_Id", String.valueOf(rider.getUser_Id()));
                        responseMap.put("Rider_Name", rider.getName());
                    } else {
                        responseMap.put("status", "Failed");
                        responseMap.put("message", "Rider not found.");
                    }
                } 
                else if ("Driver".equalsIgnoreCase(loginDto.getRole())) {
                    Optional<Driver> driverOptional = driverRepo.findByEmail(loginDto.getEmail());
                    if (driverOptional.isPresent()) {
                        Driver driver = driverOptional.get();
                        responseMap.put("UserType", "Driver");
                        responseMap.put("Driver_Id", String.valueOf(driver.getUser_Id()));
                        responseMap.put("Driver_Name", driver.getName());
                    } else {
                        responseMap.put("status", "Failed");
                        responseMap.put("message", "Driver not found.");
                    }
                } 
                else {
                    responseMap.put("status", "Failed");
                    responseMap.put("message", "Invalid role.");
                }
            } 
            else {
                responseMap.put("status", "Failed");
                responseMap.put("message", "Invalid credentials.");
            }
        } catch (Exception e) {
            responseMap.put("status", "Error");
            responseMap.put("message", "Authentication failed: " + e.getMessage());
        }

        return responseMap;
        */

    } 

}
