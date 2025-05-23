package com.example.gateway.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/gateway")
public class GatewayController {

    private final WebClient storageService;

    public GatewayController(WebClient.Builder webClientBuilder,
                             @Value("${storage.service.url}") String storageUrl) {
        this.storageService = webClientBuilder.baseUrl(storageUrl).build();
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> uploadFile(@RequestPart("file") FilePart filePart) {
        return storageService.post()
                .uri("/storage/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData("file", filePart))
                .retrieve()
                .bodyToMono(String.class)
                .onErrorResume(ex ->
                        Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_GATEWAY,
                                "Storage service не доступен",
                                ex
                        ))
                );
    }

    @GetMapping("/statistics/{id}")
    public Mono<ResponseEntity<String>> getFileStatistics(@PathVariable int id) {
        return storageService.get()
                .uri("/storage/statistics/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError,
                        response -> Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "Файл не найден")))
                .onStatus(HttpStatusCode::is5xxServerError,
                        response -> Mono.error(new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Storage service не доступен")))
                .bodyToMono(String.class)
                .map(ResponseEntity::ok)
                .onErrorResume(ResponseStatusException.class,
                        ex -> Mono.just(ResponseEntity.status(ex.getStatusCode()).body(ex.getReason())));
    }
}