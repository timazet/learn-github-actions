package com.github.timazet.greeting.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = "greeting.username1=Harry")
class GreetingControllerIT {

    private static final String ENDPOINT_GREETING = "/api/v1/greeting";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void shouldHaveCorrectResponseStructureWhenGetGreetingCalled() {
        //given
        String expectedResponse = "Hello from Harry";

        //when
        ResponseEntity<String> response = restTemplate.getForEntity(ENDPOINT_GREETING, String.class);

        //then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.hasBody()).isTrue();
        assertThat(response).extracting(ResponseEntity::getBody).isEqualTo(expectedResponse);
    }

}
