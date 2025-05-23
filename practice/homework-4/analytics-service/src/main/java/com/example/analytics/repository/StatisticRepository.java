package com.example.analytics.repository;

import com.example.analytics.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<StatisticEntity, Long> {}