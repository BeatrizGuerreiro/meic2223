package com.metrics.repository;


import org.springframework.data.jpa.repository.JpaRepository;

public interface MetricRepository extends JpaRepository<MetricsDto, String> {
}
