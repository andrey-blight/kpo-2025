package com.example.analytics.controller;

import com.example.analytics.entity.StatisticEntity;
import com.example.analytics.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/statistics/{id}")
    public ResponseEntity<StatisticEntity> getStatistics(@PathVariable int id) {
        StatisticEntity response = analyticsService.calculateStatistics(id);

        if (response == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(response);
    }
}