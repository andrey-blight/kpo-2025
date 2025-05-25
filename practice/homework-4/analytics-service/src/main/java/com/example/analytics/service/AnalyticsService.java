package com.example.analytics.service;

import com.example.analytics.entity.StatisticEntity;
import com.example.analytics.repository.StatisticRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
public class AnalyticsService {
    private final StatisticRepository statisticRepository;
    private final WebClient storageService;
    private final WebClient wordCloudService;
    private final Path storageDir = Paths.get("file-storage");

    public AnalyticsService(WebClient.Builder builder, @Value("${storage.service.url}") String baseUrl, StatisticRepository statisticRepository) throws IOException {
        this.storageService = builder.baseUrl(baseUrl).build();
        this.wordCloudService = WebClient.builder().baseUrl("https://quickchart.io").build();
        this.statisticRepository = statisticRepository;
        Files.createDirectories(storageDir);
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

        Optional<String> locationOptional = this.getWordCloud(id, text);
        if (locationOptional.isEmpty()) {
            return null;
        }
        String location = locationOptional.get();

        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setId((long) id);
        statisticEntity.setLocation(location);
        statisticEntity.setCharsCount(charsCount);
        statisticEntity.setWordCount(wordCount);

        return statisticRepository.save(statisticEntity);
    }

    public Resource getImage(int id) {
        try {
            Optional<StatisticEntity> statisticEntityOptional = statisticRepository.findById((long) id);

            if (statisticEntityOptional.isEmpty()) {
                return null;
            }

            String fileLocation = statisticEntityOptional.get().getLocation();
            Path filePath = Paths.get(fileLocation);
            System.out.println("Попытка прочитать файл по пути: " + filePath.toAbsolutePath());

            if (!Files.exists(filePath)) {
                return null;
            }
            return new FileSystemResource(filePath.toFile());

        } catch (Exception e) {
            return null;
        }
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

    public Optional<String> getWordCloud(int id, String text) {
        try {
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            byte[] imageBytes = wordCloudService.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/wordcloud")
                            .queryParam("text", encodedText)
                            .build())
                    .accept(MediaType.IMAGE_PNG)
                    .retrieve()
                    .bodyToMono(byte[].class)
                    .block();

            if (imageBytes == null || imageBytes.length == 0) {
                return Optional.empty();
            }

            Path imagePath = storageDir.resolve(id + ".jpg");
            Files.write(imagePath, imageBytes, StandardOpenOption.CREATE);

            return Optional.of(imagePath.toAbsolutePath().toString());
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}