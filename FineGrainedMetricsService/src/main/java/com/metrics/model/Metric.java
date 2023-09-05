package com.metrics.model;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Metric {

    private final String name;

    private final long value;

    private final long timestamp;

    public Metric(String name, long value, long timestamp) {
        this.name = name;
        this.value = value;
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public long getValue() {
        return value;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "Metric:" +
                "name='" + name + '\'' +
                ", value=" + value + (name.contains("time") ? "ns"  : "") +
                ", timestamp=" + getDateFromNano(timestamp) +
                '\n';
    }

    private Instant getDateFromNano(long timestamp) {
        final long seconds = TimeUnit.NANOSECONDS.toSeconds(timestamp);
        final long nanos = timestamp % 1_000_000_000L;
        return Instant.ofEpochSecond(seconds, nanos);
    }
}
