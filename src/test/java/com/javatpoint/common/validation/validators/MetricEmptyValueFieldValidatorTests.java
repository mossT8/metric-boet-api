package com.javatpoint.common.validation.validators;

import com.javatpoint.models.Metric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetricEmptyValueFieldValidatorTests {
    MetricEmptyValueFieldValidator validator = new MetricEmptyValueFieldValidator();

    @Test
    public void testBasicChecksWithNaNValue() {
        Metric metric = new Metric();
        metric.setValue(Double.NaN);
        assertTrue(validator.basicChecks(metric));
    }

    @Test
    public void testBasicChecksWithValidValue() {
        Metric metric = new Metric();
        metric.setValue(1.0);
        assertFalse(validator.basicChecks(metric));
    }

    @Test
    public void testIncorrectFormatWithNaNValue() {
        Metric metric = new Metric();
        metric.setValue(Double.NaN);
        assertTrue(validator.incorrectFormat(metric));
    }

    @Test
    public void testIncorrectFormatWithValidValue() {
        Metric metric = new Metric();
        metric.setValue(1.0);
        assertFalse(validator.incorrectFormat(metric));
    }

    @Test
    public void testGetInvalidMessage() {
        assertEquals("Metric value cannot be NaN", validator.getInvalidMessage());
    }
}
