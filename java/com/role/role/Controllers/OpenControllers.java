package com.role.role.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.role.role.Dto.LoginDto;
import com.role.role.Dto.SignupDto;
import com.role.role.Service.RiderService;
import com.role.role.ServiceImplemetation.AuthicationServiceImplementation;
import com.role.role.ServiceImplemetation.DriverServiceImplementaion;
import com.role.role.ServiceImplemetation.RiderServiceImplementaion;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/app")
@Slf4j
public class OpenControllers {
    @Autowired
    private  RiderServiceImplementaion  riderServiceImplementaion;
    @Autowired
    private DriverServiceImplementaion driverServiceImplementaion;
    @Autowired
    private  AuthicationServiceImplementation  authicationServiceImplementation;

    @GetMapping("/")
    public String getMethodName() {
        return  "Hey Welcome  to  one of the  Backend  Api ";
    }

    @PostMapping("/signup")
    public  ResponseEntity<String>  signup(@Valid @RequestBody SignupDto signupdto) {
        log.info(signupdto.toString());
        log.error(signupdto.getPassword(), "");
        if(signupdto.getRole().equals("Rider"))  riderServiceImplementaion.save(signupdto);
        else if (signupdto.getRole().equals("Driver")) driverServiceImplementaion.save(signupdto); 

        else return new ResponseEntity<String>("Error Occured", HttpStatus.REQUEST_TIMEOUT);

        return new ResponseEntity<String>("Saved the  user "+signupdto.toString(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String,String>> login(@Valid @RequestBody LoginDto loginDto) {
        //TODO: process POST request
        log.info(loginDto.toString());
        Map<String,String> map  =  authicationServiceImplementation.customAuth(loginDto);
        Iterator it  = map.entrySet().iterator();
        while (it.hasNext()) {
            log.info(it.next().toString());
        }
        return  new  ResponseEntity<Map<String,String>>( map,HttpStatus.OK);
    }
    
    
}
