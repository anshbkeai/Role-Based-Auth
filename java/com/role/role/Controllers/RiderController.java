package com.role.role.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/rider")
public class RiderController {

    @GetMapping("/test")   
    public String getMethodName() {
        return  "Hey  Rider  WELCOME  TO  TEH   aPP ";
    }
    
}
