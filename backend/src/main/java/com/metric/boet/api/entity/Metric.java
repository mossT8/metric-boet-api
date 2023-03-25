package com.metric.boet.api.entity;

import com.influxdb.annotations.Column;
import com.influxdb.annotations.Measurement;

import java.time.Instant;

/**
 * This class represents a metric that contains a name, timestamp and a value. It has three properties, name, timestamp,
 * and value, and has getters and setters for each property. The constructor accepts these three properties as
 * parameters and assigns them to the corresponding properties.
 *
 * Note: wouldn't need to extend AbstractDataBean as we are using influxDB not postgres!
 */
@Measurement(name = "metric")
public class Metric {
    @Column(tag = true)
    private String device;
    @Column(timestamp = true)
    private Instant timestamp = Instant.now();
    @Column
    private double value;
    @Column(tag = true)
    private String name;

    public Metric() {
    }

    public Metric(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Metric=[device=" + device + ",name=" + name + ", timestamp=" + timestamp + ", value=" + value + "]";
    }
}