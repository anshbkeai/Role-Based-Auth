package com.role.role.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.role.role.Dto.LoginDto;
import com.role.role.Dto.SignupDto;
import com.role.role.Pojos.Driver;

@Service
public interface DriverService {
        void  save(SignupDto signupDto );
        List<Driver>  get_Drivers();
}
