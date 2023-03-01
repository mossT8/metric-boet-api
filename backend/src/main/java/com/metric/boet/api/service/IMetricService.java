package com.metric.boet.api.service;

import com.metric.boet.api.models.Metric;
import com.metric.boet.api.exception.MetricsException;

public interface IMetricService {
    void insertMetric(Metric metric) throws MetricsException;
}
