package com.metrics.repository;

import jakarta.persistence.*;

@Entity
@Table(name = "metrics")
public class MetricsDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int aId;

    @Column(name = "metricName")
    private String bMetricName;

    @Column(name = "envId")
    private String cEnvId;

    @Column(name = "value")
    private long dValue;

    @Column(name = "timestamp")
    private long fTimestamp;

    public MetricsDto(String metricName, String envId, long value, long timestamp) {
        this.bMetricName = metricName;
        this.cEnvId = envId;
        this.dValue = value;
        this.fTimestamp = timestamp;
    }

    public int getId() {
        return aId;
    }

    public String getMetricName() {
        return bMetricName;
    }

    public String getEnvId() {
        return cEnvId;
    }

    public long getValue() {
        return dValue;
    }

    public long getTimestamp() {
        return fTimestamp;
    }

    @Override
    public String toString() {
        return "MetricsDto{" +
                "id=" + aId +
                ", metricName='" + bMetricName + '\'' +
                ", envId='" + cEnvId + '\'' +
                ", value=" + dValue +
                ", timestamp=" + fTimestamp +
                '}';
    }
}
