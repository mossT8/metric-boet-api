package com.javatpoint.common.validation.validators;

import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;

public class MetricNameMetricValidator extends AbstractMetricValidator {

    /**
     * The name of the temperature metric
     */
    public static final String METRIC_NAME_TEMPERATURE = "temperature";
    /**
     * The name of the signal metric
     */
    public static final String METRIC_NAME_SIGNAL = "signal";
    /**
     * The name of the humidity metric
     */
    public static final String METRIC_NAME_HUMIDITY = "humidity";
    /**
     * The name of the wind metric
     */
    public static final String METRIC_NAME_WIND = "wind";
    /**
     * The name of the cpu metric
     */
    public static final String METRIC_NAME_CPU = "cpu";
    /**
     * The name of the memory metric
     */
    public static final String METRIC_NAME_MEMORY = "memory";
    /**
     * The name of the disk metric
     */
    public static final String METRIC_NAME_DISK = "disk";

    /**
     * Check if the given string is a valid metric name
     *
     * @param item the Metric which name is to be checked
     * @return true if the string is a valid metric name, false otherwise
     */
    public static boolean isValidMetricName(Metric item) {
        return item.getName().equals(METRIC_NAME_TEMPERATURE)
                || item.getName().equals(METRIC_NAME_SIGNAL)
                || item.getName().equals(METRIC_NAME_HUMIDITY)
                || item.getName().equals(METRIC_NAME_WIND)
                || item.getName().equals(METRIC_NAME_CPU)
                || item.getName().equals(METRIC_NAME_MEMORY)
                || item.getName().equals(METRIC_NAME_DISK);
    }

    @Override
    public boolean incorrectFormat(Metric item) {
        return super.incorrectFormat(item) && isValidMetricName(item);
    }

    /**
     * Get the invalid metric name message
     *
     * @return the invalid metric name message
     */
    @Override
    public String getInvalidMessage() {
        return "Invalid metric name, should be one of " + METRIC_NAME_TEMPERATURE + "," + METRIC_NAME_SIGNAL + "," + METRIC_NAME_HUMIDITY + "," + METRIC_NAME_WIND + "," + METRIC_NAME_CPU + "," + METRIC_NAME_MEMORY + "," + METRIC_NAME_DISK + ".";
    }
}
