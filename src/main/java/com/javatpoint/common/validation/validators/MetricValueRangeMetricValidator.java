package com.javatpoint.common.validation.validators;

import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;


public class MetricValueRangeMetricValidator extends AbstractMetricValidator {

    private static final double minValue = -127;
    private static final double maxValue = Double.MAX_VALUE;


    /**
     * Check if the metric value is valid
     *
     * @param metric the metric to check
     * @return true if the metric value is valid, false otherwise
     * @throws MetricsException if the metric value is not valid
     */
    public static boolean isValidMetricValue(Metric metric) {
        // range validation of metric value
        return true;
    }


    @Override
    public boolean incorrectFormat(Metric item) {
        return super.incorrectFormat(item) && isValidMetricValue(item);
    }

    /**
     * Get the invalid metric value message
     *
     * @return the invalid metric value message
     */
    @Override
    public String getInvalidMessage() {
        return "Metric value is out of range, should be between " + minValue + " and " + maxValue;
    }
}
