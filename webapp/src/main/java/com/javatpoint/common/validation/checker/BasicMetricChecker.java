package com.javatpoint.common.validation.checker;


import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.validation.validators.generic.IMetricValidator;
import com.javatpoint.models.Metric;

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