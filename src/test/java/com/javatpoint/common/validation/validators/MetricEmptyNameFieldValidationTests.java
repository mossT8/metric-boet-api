package com.javatpoint.common.validation.validators;

import com.javatpoint.models.Metric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetricEmptyNameFieldValidationTests {
    MetricEmptyNameFieldValidator validator = new MetricEmptyNameFieldValidator();

    @Test
    public void testBasicChecksWithNullName() {
        Metric metric = new Metric();
        metric.setName(null);
        assertTrue(validator.basicChecks(metric));
    }

    @Test
    public void testBasicChecksWithEmptyName() {
        Metric metric = new Metric();
        metric.setName("");
        assertTrue(validator.basicChecks(metric));
    }

    @Test
    public void testBasicChecksWithValidName() {
        Metric metric = new Metric();
        metric.setName("valid_name");
        assertFalse(validator.basicChecks(metric));
    }

    @Test
    public void testIncorrectFormatWithNullName() {
        Metric metric = new Metric();
        metric.setName(null);
        assertTrue(validator.incorrectFormat(metric));
    }

    @Test
    public void testIncorrectFormatWithEmptyName() {
        Metric metric = new Metric();
        metric.setName("");
        assertTrue(validator.incorrectFormat(metric));
    }

    @Test
    public void testIncorrectFormatWithValidName() {
        Metric metric = new Metric();
        metric.setName("valid_name");
        assertFalse(validator.incorrectFormat(metric));
    }

    @Test
    public void testGetInvalidMessage() {
        assertEquals("Metric name cannot be null or empty", validator.getInvalidMessage());
    }
}
