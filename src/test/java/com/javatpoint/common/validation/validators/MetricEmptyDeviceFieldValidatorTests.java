package com.javatpoint.common.validation.validators;

import com.javatpoint.models.Metric;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MetricEmptyDeviceFieldValidatorTests {
    MetricEmptyDeviceFieldValidator validator = new MetricEmptyDeviceFieldValidator();

    @Test
    public void testBasicChecksWithNullName() {
        Metric metric = new Metric();
        metric.setDevice(null);
        assertTrue(validator.basicChecks(metric));
    }

    @Test
    public void testBasicChecksWithEmptyName() {
        Metric metric = new Metric();
        metric.setDevice("");
        assertTrue(validator.basicChecks(metric));
    }

    @Test
    public void testBasicChecksWithValidName() {
        Metric metric = new Metric();
        metric.setDevice("valid_device");
        assertFalse(validator.basicChecks(metric));
    }

    @Test
    public void testIncorrectFormatWithNullName() {
        Metric metric = new Metric();
        metric.setDevice(null);
        assertTrue(validator.incorrectFormat(metric));
    }

    @Test
    public void testIncorrectFormatWithEmptyName() {
        Metric metric = new Metric();
        metric.setDevice("");
        assertTrue(validator.incorrectFormat(metric));
    }

    @Test
    public void testIncorrectFormatWithValidName() {
        Metric metric = new Metric();
        metric.setDevice("valid_device");
        assertFalse(validator.incorrectFormat(metric));
    }

    @Test
    public void testGetInvalidMessage() {
        assertEquals("Metric device cannot be null or empty", validator.getInvalidMessage());
    }
}