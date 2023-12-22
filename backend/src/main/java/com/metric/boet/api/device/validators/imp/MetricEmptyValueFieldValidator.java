package com.metric.boet.api.device.validators.imp;

import com.metric.boet.api.device.validators.AbstractMetricValidator;
import com.metric.boet.api.entity.Metric;

public class MetricEmptyValueFieldValidator extends AbstractMetricValidator {
    /**
     * Check if the given metric's value is NaN
     *
     * @param metric Metric to check
     * @return true if the metric's value is NaN, false otherwise
     */
    public boolean basicChecks(Metric metric) {
        return Double.isNaN(metric.getValue());
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
        return "Metric value cannot be NaN";
    }
}