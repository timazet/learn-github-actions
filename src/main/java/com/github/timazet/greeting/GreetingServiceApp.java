package com.github.timazet.greeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.github.timazet.greeting.config")
public class GreetingServiceApp {

    public static void main(String[] args) {
        SpringApplication.run(GreetingServiceApp.class, args);
    }
}