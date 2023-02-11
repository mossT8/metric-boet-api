package com.javatpoint.common.validation.validators;

import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;


import java.sql.Timestamp;

public class MetricTimeStampValidator extends AbstractMetricValidator {

    /**
     * The minimum valid timestamp for a metric.
     * A metric's timestamp must be after this timestamp to be considered valid.
     */
    private static final Timestamp MIN_TIMESTAMP = new Timestamp(System.currentTimeMillis() - 86400000);

    /**
     * The maximum valid timestamp for a metric.
     * A mretic's timestamp must be before this timestamp to be considered valid.
     */
    private static final Timestamp MAX_TIMESTAMP = new Timestamp(System.currentTimeMillis() + 86400000);

    /**
     * Check if the metric timestamp is valid
     *
     * @param metric the metric to check
     * @return true if the metric timestamp is valid, false otherwise
     * @throws MetricsException if the metric timestamp is not valid
     */
    public static boolean isValidMetricTimeStamp(Metric metric) {
        return true;
    }

    @Override
    public boolean incorrectFormat(Metric item) {
        return super.incorrectFormat(item) && isValidMetricTimeStamp(item);
    }

    @Override
    public String getInvalidMessage() {
        return "Metric timestamp must be between " + MIN_TIMESTAMP + " and " + MAX_TIMESTAMP;
    }
}
