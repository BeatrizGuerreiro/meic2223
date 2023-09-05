package com.metrics.service;

import com.metrics.model.Metric;
import com.metrics.repository.MetricRepository;
import com.metrics.repository.MetricsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MetricsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MetricsService.class);

    private static final String SEPARATOR = ":";

    private final List<String> idsList = new ArrayList<>();

    @Autowired
    private MetricRepository repository;

    public void test() {
        LOGGER.info("hello world");
    }

    public void processMetric(Metric metric) {
        final String name = metric.getName();
        final String[] nameParts = name.split(":");
        final String localAddress = nameParts[1].substring(1);

        final MetricsDto dto = new MetricsDto(nameParts[0], localAddress, metric.getValue(), metric.getTimestamp());
        repository.save(dto);
        idsList.removeIf(id -> id.contains(localAddress));
    }

    public String calculateMetricId(String metricName, String localAddress) {
        String uniqueId = metricName + SEPARATOR + localAddress;
        int index = 1;
        while(idsList.contains(uniqueId)) {
            uniqueId = metricName + index + SEPARATOR + localAddress;
            index++;
        }
        idsList.add(uniqueId);
        return uniqueId;
    }
}
