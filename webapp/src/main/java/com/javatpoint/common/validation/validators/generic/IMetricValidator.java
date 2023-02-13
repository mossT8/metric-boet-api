package com.javatpoint.common.validation.validators.generic;

import com.javatpoint.models.Metric;

public interface IMetricValidator {
    /**
     * Validate the Metric
     *
     * @param item Metric to validate
     * @return True if the Metric is valid, False otherwise
     */
    boolean incorrectFormat(Metric item);

    /**
     * Returns the invalid message for this validator
     *
     * @return Invalid message
     */
    String getInvalidMessage();
}