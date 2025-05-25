package com.cyat;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to E-Commerce Backend API";
    }
}
