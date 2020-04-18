package com.oreilly.demo.controllers;

import com.oreilly.demo.entities.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// this will startup a test web server on a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloRestControllerIntegrationTest {

    @Test
    public void greetWithoutName(@Autowired TestRestTemplate template, @LocalServerPort int randomServerPort) {
        ResponseEntity<Greeting> entity = template.getForEntity("/rest", Greeting.class);
        Greeting response = entity.getBody();
        assertAll(
                () -> assertEquals(HttpStatus.OK, entity.getStatusCode()),
                () -> assertEquals(MediaType.APPLICATION_JSON, entity.getHeaders().getContentType()),
                () -> assertNotNull(response),
                () -> assertEquals("Hello, World!", response.getMessage()));
    }

    @Test
    public void greetWithName(@Autowired TestRestTemplate template) {
        Greeting response = template.getForObject("/rest?name=Dolly", Greeting.class);
        assertEquals("Hello, Dolly!", response.getMessage());
    }

}