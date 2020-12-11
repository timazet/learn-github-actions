package com.github.timazet.greeting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.timazet.greeting.config.GreetingProperties;

@RestController
public class GreetingController {

    private static final Logger log = LoggerFactory.getLogger(GreetingController.class);

    private final String username;

    public GreetingController(GreetingProperties properties) {
        this.username = properties.getUsername();
        log.info("Using following configuration: 'username' - {}", username);
    }

    @GetMapping("/api/v1/greeting")
    public String getGreeting() {
        return "Hello from " + username;
    }

}
