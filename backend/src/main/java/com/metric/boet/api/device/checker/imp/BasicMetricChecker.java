package com.metric.boet.api.device.checker.imp;


import com.metric.boet.api.device.checker.IMetricChecker;
import com.metric.boet.api.device.validators.IMetricValidator;
import com.metric.boet.api.util.exception.MetricsException;
import com.metric.boet.api.entity.Metric;

public class BasicMetricChecker implements IMetricChecker {
    /**
     * Validate a Metric using a given IMetricValidator
     *
     * @param validator IMetricValidator to use
     * @param metric    Metric to validate
     * @throws MetricsException if the Metric is invalid
     */
    @Override
    public void isValid(IMetricValidator validator, Metric metric) throws MetricsException {
        if (validator.incorrectFormat(metric)) {
            throw new MetricsException(validator.getInvalidMessage());
        }
    }
}