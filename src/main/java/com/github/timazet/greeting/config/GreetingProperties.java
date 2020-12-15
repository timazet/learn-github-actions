package com.github.timazet.greeting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Validated
@ConfigurationProperties("greeting")
public class GreetingProperties {

    /**
     * Username that is used in email signature.
     */
    @NotBlank
    @Size(min = 5, max = 100)
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}