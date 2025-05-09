package com.project.catalog;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@Import(TestcontainersConfiguration.class)
public class AbstractIntegrationTest {

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp(){
        //Both of the ways are perfect.
        //RestAssured.baseURI =  "http://localhost:" + port;
        RestAssured.port = port;

    }

}
