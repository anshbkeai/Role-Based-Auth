package com.role.role.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.role.role.Dto.LoginDto;
import com.role.role.Dto.SignupDto;
import com.role.role.Pojos.Rider;

@Service
public interface RiderService {
    void  save(SignupDto signupDto);
    List<Rider>  get_all();
}
