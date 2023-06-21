package com.metrics.controller;

import com.metrics.model.Metric;
import com.metrics.service.MetricsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    @Autowired
    private MetricsService service;

    @GetMapping
    public String index() {
        service.test();
        return "Greetings from Spring Boot!";
    }

    @PostMapping
    public String postMetric(@RequestBody List<Metric> input)  {
        input.forEach(service::processMetric);
        return input.toString();
    }
}
