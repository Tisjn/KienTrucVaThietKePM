package com.example.service_b;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceBController {

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello from Service B";
    }
}

