package com.metric.boet.api.influxdb;

import com.metric.boet.api.entity.Metric;

public interface IInfluxDBService {
    void pushMetricToInflux(Metric metric);
}
