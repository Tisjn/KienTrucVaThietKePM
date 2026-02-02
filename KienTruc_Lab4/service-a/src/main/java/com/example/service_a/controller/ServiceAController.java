package com.example.service_a.controller;

import com.example.service_a.service.ServiceAService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    private final ServiceAService serviceAService;

    public ServiceAController(ServiceAService serviceAService) {
        this.serviceAService = serviceAService;
    }

    @GetMapping("/call-b")
    public String callServiceB() {
        return serviceAService.callServiceB();
    }
}
