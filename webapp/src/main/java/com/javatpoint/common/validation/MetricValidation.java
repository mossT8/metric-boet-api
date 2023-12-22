package com.javatpoint.common.validation;


import com.javatpoint.common.exception.MetricsException;
import com.javatpoint.common.influxdb.InfluxDBService;
import com.javatpoint.common.log.LogManager;
import com.javatpoint.common.validation.checker.BasicMetricChecker;
import com.javatpoint.common.validation.validators.*;
import com.javatpoint.common.validation.validators.generic.AbstractMetricValidator;
import com.javatpoint.models.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * MetricValidation is a final class that provides validation checks for a Metric object.
 * It contains an array of AbstractMetricValidator objects and a BasicMetricChecker object.
 * The checkMetric method iterates through the array of validators and calls the isValid method on the BasicMetricChecker,
 * passing in the current validator and the Metric object. If any of the validators return false,
 * the BasicMetricChecker will throw a MetricsException with the corresponding error message.
 */
@Component
public final class MetricValidation {

    private LogManager logger;
    private InfluxDBService influxDBService;

    @Autowired
    public MetricValidation(LogManager logger, InfluxDBService influxDBService) {
        this.logger = logger;
        this.influxDBService = influxDBService;
    }

    private static final String DIVIDER = "_";
    private static final String VALIDATING_PREFIX = "VALIDATING";
    private static final String CREATED_PREFIX = "CREATED";
    private static final String FAILED_PREFIX = "FAILED";

    //array of different validator implementations
    private static final AbstractMetricValidator[] validators = new AbstractMetricValidator[]{
            new MetricEmptyNameFieldValidator(),
            new MetricEmptyValueFieldValidator(),
            new MetricEmptyDeviceFieldValidator()
    };

    //checker object that implements the IMetricChecker interface
    private static final BasicMetricChecker checker = new BasicMetricChecker();

    //iterates through all the validators and call isValid method on the checker object
    public void checkMetric(Metric metric) throws MetricsException {
        logger.logInfo(VALIDATING_PREFIX.concat(DIVIDER).concat(metric.toString()));
        for (AbstractMetricValidator metricValidator : validators) {
            try {
                checker.isValid(metricValidator, metric);
            } catch (MetricsException e) {
                logger.logInfo(FAILED_PREFIX.concat(DIVIDER).concat(metric.toString()));
                logger.logError(e.getMessage());
                e.printStackTrace();
                throw e;
            }
        }
        influxDBService.pushMetricToInflux(metric);
        logger.logInfo(CREATED_PREFIX.concat(DIVIDER).concat(metric.toString()));
    }
}