package com.metric.boet.api.core.validation.validators;


import com.metric.boet.api.entity.Metric;

public abstract class AbstractMetricValidator implements IMetricValidator {

    /**
     * Validate the Metric
     *
     * @param item Metric to validate
     * @return True if the Metric is valid, False otherwise
     */
    @Override
    public boolean incorrectFormat(Metric item) {
        return false;
    }

    /**
     * Returns the invalid message for this validator
     *
     * @return Invalid message
     */
    @Override
    public String getInvalidMessage() {
        return "Item invalid";
    }
}
