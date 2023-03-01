package com.metric.boet.api.service.imp;

import com.metric.boet.api.exception.MetricsException;
import com.metric.boet.api.common.validation.MetricValidation;
import com.metric.boet.api.models.Metric;
import com.metric.boet.api.service.IMetricService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MetricService implements IMetricService {

    private MetricValidation metricValidation;

    @Autowired
    public MetricService(MetricValidation metricValidation) {
        this.metricValidation = metricValidation;
    }

    /**
     * Inserts a new metric into the memory store.
     *
     * @param metric the metric to insert
     * @throws MetricsException if the metric is invalid
     */
    @Override
    public void insertMetric(Metric metric) throws MetricsException {
        metricValidation.checkMetric(metric);
    }
}
