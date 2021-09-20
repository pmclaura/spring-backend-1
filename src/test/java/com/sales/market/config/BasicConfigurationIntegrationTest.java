/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.config;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BasicConfigurationIntegrationTest {

    TestRestTemplate restTemplate;
    URL base;
    @LocalServerPort
    int port;

    @BeforeEach
    public void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user", "user");
        base = new URL("http://localhost:" + port + "/buys");
    }

//    @Test
    public void whenLoggedUserRequestsHomePage_ThenSuccess()
            throws IllegalStateException {
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    //    @Test
    public void whenUserWithWrongCredentials_thenUnauthorizedPage() {

        restTemplate = new TestRestTemplate("user", "wrongpassword");
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    //    @Test
    public void whenUserWithWrongRole_thenUnauthorizedPage() throws MalformedURLException {

        restTemplate = new TestRestTemplate("user", "user");
        base = new URL("http://localhost:" + port + "/buys/admin");
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    //    @Test
    public void whenUserWithRightRole_ThenSuccess() throws MalformedURLException {

        restTemplate = new TestRestTemplate("admin", "admin");
        base = new URL("http://localhost:" + port + "/buys/admin");
        ResponseEntity<String> response =
                restTemplate.getForEntity(base.toString(), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
