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

    @Column(name = "functionName")
    private String cFunctionName;

    @Column(name = "envId")
    private String dEnvId;

    @Column(name = "value")
    private long eValue;

    @Column(name = "timestamp")
    private long fTimestamp;

    public MetricsDto(String metricName, String functionName, String envId, long value, long timestamp) {
        this.bMetricName = metricName;
        this.cFunctionName = functionName;
        this.dEnvId = envId;
        this.eValue = value;
        this.fTimestamp = timestamp;
    }

    public int getId() {
        return aId;
    }

    public String getMetricName() {
        return bMetricName;
    }

    public String getFunctionName() {
        return cFunctionName;
    }

    public String getEnvId() {
        return dEnvId;
    }

    public long getValue() {
        return eValue;
    }

    public long getTimestamp() {
        return fTimestamp;
    }

    @Override
    public String toString() {
        return "MetricsDto{" +
                "id=" + aId +
                ", metricName='" + bMetricName + '\'' +
                ", functionName='" + cFunctionName + '\'' +
                ", envId='" + dEnvId + '\'' +
                ", value=" + eValue +
                ", timestamp=" + fTimestamp +
                '}';
    }
}
