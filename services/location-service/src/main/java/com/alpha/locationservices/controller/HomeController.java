package com.alpha.locationservices.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping()
    public String HomeController(){
        return "Hello everyone i am location service of airline-system";
    }
}
