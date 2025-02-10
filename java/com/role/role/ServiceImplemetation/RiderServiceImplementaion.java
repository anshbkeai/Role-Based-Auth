package com.role.role.ServiceImplemetation;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.web.server.ServerSecurityMarker;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.role.role.Dto.LoginDto;
import com.role.role.Dto.SignupDto;
import com.role.role.Pojos.App_User;
import com.role.role.Pojos.Rider;
import com.role.role.Pojos.Role;
import com.role.role.Repositry.RiderRepo;
import com.role.role.Service.RiderService;

@Service
public class RiderServiceImplementaion implements  RiderService{

    @Autowired
    private  RiderRepo  riderRepo;
    @Autowired
    private  PasswordEncoder  passwordEncoder;

    @Override
    public void save(SignupDto loginDto) {
        // TODO Auto-generated method stub
        Rider  rider  =  new Rider();
         rider.setUser_Id(UUID.randomUUID().toString());
         rider.setName(loginDto.getName());
         rider.setEmail(loginDto.getEmail());
         rider.setPassword(passwordEncoder.encode(loginDto.getPassword()));
         rider.setDate_joined(LocalDate.now());
         rider.setPhone_Number(loginDto.getPhone());
        //hey  you  will  end  that   FROM THE   uL 
        rider.setSet(new  HashSet<>(List.of(Role.Rider)));
     

        riderRepo.save(rider);


    }

    @Override
    public List<Rider> get_all() {
        // TODO Auto-generated method stub
        return  riderRepo.findAll();
    }

}
