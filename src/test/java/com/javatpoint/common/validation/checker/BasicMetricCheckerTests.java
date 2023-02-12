package com.javatpoint.common.validation.checker;

import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.influxdb.InfluxDBService;
import com.javatpoint.common.log.LogManager;
import com.javatpoint.common.validation.MetricValidation;
import com.javatpoint.models.Metric;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
class MetricValidationTest {
    @Mock
    private LogManager mockLogger;

    @Mock
    private InfluxDBService mockInfluxDBService;

    @InjectMocks
    private MetricValidation metricValidation;

    @Test
    public void testCheckMetric() throws MetricsException {
        Metric metric = new Metric("device1", 12.0);
        metricValidation.checkMetric(metric);
        verify(mockInfluxDBService, times(1)).pushMetricToInflux(metric);
        verify(mockLogger, times(1)).logInfo("VALIDATING_device1, timestamp=1234, value=12.0");
        verify(mockLogger, times(1)).logInfo("CREATED_device1, timestamp=1234, value=12.0");
    }

    @Test(expected = MetricsException.class)
    public void testCheckMetric_throwsException() throws MetricsException {
        Metric metric = new Metric("", 12.0);
        metricValidation.checkMetric(metric);
    }
}
