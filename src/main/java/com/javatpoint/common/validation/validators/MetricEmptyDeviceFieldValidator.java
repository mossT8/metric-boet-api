package com.javatpoint.common.validation.validators;

import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;

public class MetricEmptyDeviceFieldValidator extends AbstractMetricValidator {
    /**
     * Performs basic checks on the metric name field
     *
     * @param metric Metric to check
     * @return True if the name field is null or empty, False otherwise
     */
    public boolean basicChecks(Metric metric) {
        // basic empty checks
        return metric.getDevice() == null || metric.getDevice().length() == 0;
    }

    /**
     * Validates that the Metric's name field is not null or empty
     *
     * @param item Metric to validate
     * @return True if the name field is not null or empty, False otherwise
     */
    @Override
    public boolean incorrectFormat(Metric item) {
        return super.incorrectFormat(item) && basicChecks(item);
    }

    /**
     * Returns the invalid message for this validator
     *
     * @return Invalid message
     */
    @Override
    public String getInvalidMessage() {
        return "Metric name cannot be null or empty";
    }
}
