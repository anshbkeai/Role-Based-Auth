package com.role.role.ServiceImplemetation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.role.role.Dto.LoginDto;
import com.role.role.Dto.SignupDto;
import com.role.role.Pojos.Driver;

import com.role.role.Pojos.Role;
import com.role.role.Repositry.DriverRepo;
import com.role.role.Service.DriverService;

@Service
public class DriverServiceImplementaion implements  DriverService{

    @Autowired
    private  DriverRepo  driverRepo;

    @Autowired
    private  PasswordEncoder  passwordEncoder;
    @Override
    public void save(SignupDto loginDto) {
        // TODO Auto-generated method stub
         Driver  driver  =  new Driver();
         driver.setUser_Id(UUID.randomUUID().toString());
         driver.setName(loginDto.getName());
         driver.setEmail(loginDto.getEmail());
         driver.setPassword(passwordEncoder.encode(loginDto.getPassword()));
         driver.setDate_Joined(LocalDate.now());
         driver.setPhone_Number(loginDto.getPhone());
        //river. you  will  end  that   FROM THE   uL 
        driver.setSet(new  HashSet<>(List.of(Role.Driver)));
        driver.setDate_Joined(LocalDate.now()); 

        driverRepo.save(driver);

    }

    @Override
    public List<Driver> get_Drivers() {
        // TODO Auto-generated method stub
        return  driverRepo.findAll();
    }

}
