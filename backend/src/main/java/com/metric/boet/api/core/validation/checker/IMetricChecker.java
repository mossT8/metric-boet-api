package com.metric.boet.api.core.validation.checker;

import com.metric.boet.api.exception.MetricsException;
import com.metric.boet.api.core.validation.validators.IMetricValidator;
import com.metric.boet.api.entity.Metric;

public interface IMetricChecker {
    /**
     * Validate a Metric using a given IMetricValidator
     *
     * @param validator IMetricValidator to use
     * @param metric    Metric to validate
     * @throws MetricsException if the Metric is invalid
     */
    void isValid(IMetricValidator validator, Metric metric) throws MetricsException;
}