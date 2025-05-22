package com.example.analytics.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AnalyticsService {
    private int countWords(String text) {
        if (text == null || text.isEmpty()) return 0;
        String[] words = text.trim().split("\\s+");
        return words.length;
    }

    public Map<String, Integer> calculateStatistics(int id) {

        String text = getTextById(id);
        if (text == null) {
            return null;
        }

        int wordCount = countWords(text);
        int charsCount = text.length();

        Map<String, Integer> result = new HashMap<>();
        result.put("word_count", wordCount);
        result.put("chars_count", charsCount);
        return result;
    }

    private String getTextById(int id) {
        if (id == 1) {
            return "Hello world! This is a test.";
        }
        return null;
    }
}