package com.example.gateway.controller;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GatewayControllerTest {

    private WireMockServer wireMockServer;

    @Autowired
    private WebTestClient webTestClient;

    @DynamicPropertySource
    static void overrideProperties(DynamicPropertyRegistry registry) {
        registry.add("storage.service.url", () -> "http://localhost:9561");
    }

    @BeforeAll
    void startWireMock() {
        wireMockServer = new WireMockServer(9561);
        wireMockServer.start();
    }

    @AfterAll
    void stopWireMock() {
        wireMockServer.stop();
    }

    @Test
    void uploadFile200() {
        wireMockServer.stubFor(post(urlEqualTo("/storage/upload"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "text/plain")
                        .withBody("upload successful")));

        webTestClient.post()
                .uri("/gateway/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new ClassPathResource("a.txt")))
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class).isEqualTo("upload successful");
    }

    @Test
    void uploadFile502() {
        wireMockServer.stubFor(post(urlEqualTo("/storage/upload"))
                .willReturn(aResponse()
                        .withStatus(500)));

        webTestClient.post()
                .uri("/gateway/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", new ClassPathResource("a.txt")))
                .exchange()
                .expectStatus().isEqualTo(HttpStatus.BAD_GATEWAY.value())
                .expectBody(String.class).value(body ->
                        assertTrue(body.contains("Bad Gateway")));
    }
}
