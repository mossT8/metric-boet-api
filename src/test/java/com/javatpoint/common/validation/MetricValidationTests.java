package com.javatpoint.common.validation;

import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.influxdb.InfluxDBService;
import com.javatpoint.common.log.LogManager;
import com.javatpoint.common.validation.checker.BasicMetricChecker;
import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MetricValidationTests {
    private static final BasicMetricChecker checker = new BasicMetricChecker();

    @Mock
    private LogManager logger;

    @Mock
    private InfluxDBService influxDBService;

    @InjectMocks
    private MetricValidation metricValidation;

    @Test
    public void testCheckMetric_ValidMetric() throws MetricsException {
        Metric metric = new Metric();
        doNothing().when(influxDBService).pushMetricToInflux(any(Metric.class));

        metricValidation.checkMetric(metric);

        verify(logger).logInfo("VALIDATING_".concat(metric.toString()));
        verify(influxDBService).pushMetricToInflux(metric);
        verify(logger).logInfo("CREATED_".concat(metric.toString()));
    }

    @Test
    public void testCheckMetric_InvalidMetric() throws MetricsException {
        Metric metric = new Metric();
        metric.setName(null);
        doThrow(MetricsException.class).when(checker).isValid(any(AbstractMetricValidator.class), any(Metric.class));

        metricValidation.checkMetric(metric);

        verify(logger).logInfo("VALIDATING_".concat(metric.toString()));
        verify(logger).logInfo("FAILED_".concat(metric.toString()));
        verify(logger).logError(any(String.class));
    }
}
