package com.metrics.service;

import com.metrics.model.Metric;
import com.metrics.repository.MetricRepository;
import com.metrics.repository.MetricsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsService.class);

    @Autowired
    private MetricRepository repository;

    public void test() {
        LOGGER.info("hello world");
    }

    public void processMetric(Metric metric) {
        final String name = metric.getName();
        final String[] nameParts = name.split(":");
        final MetricsDto dto = new MetricsDto(nameParts[0], nameParts[1], nameParts[2].substring(1), metric.getValue(), metric.getTimestamp());
        repository.save(dto);
    }
}
