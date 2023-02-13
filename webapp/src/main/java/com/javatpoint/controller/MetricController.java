package com.javatpoint.controller;

import com.javatpoint.common.log.LogManager;
import com.javatpoint.models.Metric;
import com.javatpoint.services.MetricService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

/**
 * MetricResources responsible for the data being accessed
 */
@RestController
@RequestMapping("/metrics")
public class MetricController {

    private final MetricService metricService;
    private final LogManager logger;

    @Autowired
    public MetricController(MetricService metricService, LogManager logger) {
        this.logger = logger;
        this.metricService = metricService;
    }

    /**
     * Endpoint for inserting a metric
     *
     * @param metric The Metric object to be inserted
     * @return HTTP response with status code 201 (CREATED) if successful, 400 (BAD_REQUEST) if an error occurs
     */
    @PostMapping("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<Void> insertMetric(@RequestBody Metric metric) {
        try {
            metricService.insertMetric(metric);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String stackTrace = sw.toString();
            logger.logError("Error Message " + stackTrace + " for /add \n Stack Trace \n" + stackTrace);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
