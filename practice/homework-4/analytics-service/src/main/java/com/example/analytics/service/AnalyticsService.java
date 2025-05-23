package com.example.analytics.service;

import com.example.analytics.entity.StatisticEntity;
import com.example.analytics.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service
public class AnalyticsService {
    private final StatisticRepository statisticRepository;
    private final WebClient storageService;

    public AnalyticsService(WebClient.Builder builder, @Value("${storage.service.url}") String baseUrl, StatisticRepository statisticRepository) {
        this.storageService = builder.baseUrl(baseUrl).build();
        this.statisticRepository = statisticRepository;
    }

    private int countWords(String text) {
        if (text == null || text.isEmpty()) return 0;
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public StatisticEntity calculateStatistics(int id) {

        if (statisticRepository.findById((long) id).isPresent()) {
            return statisticRepository.findById((long) id).get();
        }

        Optional<String> textOptional = getTextById(id);
        if (textOptional.isEmpty()) {
            return null;
        }
        String text = textOptional.get();
        int wordCount = countWords(text);
        int charsCount = text.length();

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setId((long) id);
        statisticEntity.setCharsCount(charsCount);
        statisticEntity.setWordCount(wordCount);

        return statisticRepository.save(statisticEntity);
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