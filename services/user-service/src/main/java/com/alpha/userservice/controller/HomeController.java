package com.alpha.userservice.controller;

import com.alpha.payload.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping
    public ApiResponse HomeController(){
        return new ApiResponse("Welcome to the user service of the airlines system");
    }
}
