package com.javatpoint.common.validation.checker;

import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.validation.validators.generic.IMetricValidator;
import com.javatpoint.models.Metric;

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