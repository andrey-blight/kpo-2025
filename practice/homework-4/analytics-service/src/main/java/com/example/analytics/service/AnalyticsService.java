package com.example.analytics.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AnalyticsService {
    private final WebClient storageService;

    public AnalyticsService(WebClient.Builder builder, @Value("${storage.service.url}") String baseUrl) {
        this.storageService = builder.baseUrl(baseUrl).build();
    }

    private int countWords(String text) {
        if (text == null || text.isEmpty()) return 0;
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public Map<String, Integer> calculateStatistics(int id) {

        Optional<String> textOptional = getTextById(id);
        if (textOptional.isEmpty()) {
            return null;
        }
        String text = textOptional.get();
        int wordCount = countWords(text);
        int charsCount = text.length();

        Map<String, Integer> result = new HashMap<>();
        result.put("word_count", wordCount);
        result.put("chars_count", charsCount);
        return result;
    }

    private Optional<String> getTextById(int id) {
        try {
            String result = storageService.get()
                    .uri("/storage/content/{id}", id)
                    .retrieve()
                    .onStatus(
                            HttpStatusCode::is4xxClientError,
                            clientResponse -> Mono.empty() // не выбрасываем исключение
                    )
                    .onStatus(
                            HttpStatusCode::is5xxServerError,
                            clientResponse -> Mono.empty() // тоже не выбрасываем
                    )
                    .bodyToMono(String.class)
                    .block();

            return Optional.ofNullable(result);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}