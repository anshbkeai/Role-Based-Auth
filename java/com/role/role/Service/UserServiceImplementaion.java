package com.role.role.Service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.role.role.Pojos.Driver;
import com.role.role.Pojos.Rider;
import com.role.role.Repositry.DriverRepo;
import com.role.role.Repositry.RiderRepo;

@Service
public class UserServiceImplementaion implements  UserDetailsService {

    @Autowired 
    private  RiderRepo riderRepo;
     @Autowired 
     private  DriverRepo driverRepo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        Optional<Rider>  rider  =  riderRepo.findByEmail(username);
        if (rider.isPresent()) {
            return rider.get();  // No need to cast, Rider is already a UserDetails
        }
    
        Optional<Driver> driver = driverRepo.findByEmail(username);
        if (driver.isPresent()) {
            return driver.get();  // No need to cast, Driver is already a UserDetails
        }
    
        throw new UsernameNotFoundException("User not found"); //  you  need  to  throw  tjhem   
    }

}
