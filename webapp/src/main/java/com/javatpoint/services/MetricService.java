package com.javatpoint.services;

import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.log.LogManager;
import com.javatpoint.common.validation.MetricValidation;
import com.javatpoint.models.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MetricService {

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
    public void insertMetric(Metric metric) throws MetricsException {
        metricValidation.checkMetric(metric);
    }
}
