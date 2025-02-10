package com.role.role.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestContoller {

    @GetMapping("/test")
    public  String  t() {
        return  "hello";
    }
}
