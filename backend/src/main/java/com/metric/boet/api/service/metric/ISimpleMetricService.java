package com.metric.boet.api.service.metric;

import com.metric.boet.api.entity.Metric;
import com.metric.boet.api.exception.MetricsException;

public interface ISimpleMetricService {
    void insertMetric(Metric metric) throws MetricsException;
}
