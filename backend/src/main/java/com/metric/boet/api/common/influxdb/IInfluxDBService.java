package com.metric.boet.api.common.influxdb;

import com.metric.boet.api.models.Metric;

public interface IInfluxDBService {
    void pushMetricToInflux(Metric metric);
}
