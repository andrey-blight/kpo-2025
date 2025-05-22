package com.example.analytics.controller;

import com.example.analytics.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<Map<String, Integer>> getStatistics(@PathVariable int id) {
        Map<String, Integer> stats = analyticsService.calculateStatistics(id);

        if (stats == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(stats);
    }
}