package com.javatpoint.common.validation.validators;

import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;

public class MetricEmptyTimeStampFieldValidator extends AbstractMetricValidator {
    /**
     * Check if the given metric's timestamp is null
     *
     * @param metric Metric to check
     * @return true if the metric's timestamp is null, false otherwise
     */
    private boolean basicChecks(Metric metric) {
        return metric.getTimestamp() == null;
    }

    /**
     * Validate a Metric
     *
     * @param item Metric to validate
     * @return true if the Metric is valid, false otherwise
     */
    @Override
    public boolean incorrectFormat(Metric item) {
        return super.incorrectFormat(item) && basicChecks(item);
    }

    /**
     * Get the error message for an invalid Metric
     *
     * @return error message
     */
    @Override
    public String getInvalidMessage() {
        return "Metric timestamp cannot be null";
    }
}
