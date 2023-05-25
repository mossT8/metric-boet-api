package com.metric.boet.api.service.metric;

import com.metric.boet.api.device.MetricValidation;
import com.metric.boet.api.entity.Metric;
import com.metric.boet.api.util.exception.MetricsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleMetricServiceTests implements ISimpleMetricService {

    private MetricValidation metricValidation;

    @Autowired
    public SimpleMetricServiceTests(MetricValidation metricValidation) {
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
