package com.example.service_a.service;

import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ServiceAService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Retry(name = "serviceBRetry", fallbackMethod = "fallback")
    public String callServiceB() {
        System.out.println("Calling Service B...");
        return restTemplate.getForObject(
                "http://localhost:8081/api/hello",
                String.class
        );
    }

    public String fallback(Throwable e) {
        System.out.println("Fallback called");
        return "Service B unavailable";
    }
}

