package com.javatpoint.common.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.javatpoint.models.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class InfluxDBService {

    private InfluxDBClient influxDBClient;
    private WriteApiBlocking writeApi;

    String bucket = "metrics";
    String org = "metric-boet-api";

    @Autowired
    public InfluxDBService(InfluxDBClient influxDBClient) {
        this.influxDBClient = influxDBClient;
        this.writeApi = influxDBClient.getWriteApiBlocking();
    }

    public void pushMetricToInflux(Metric metric) {
        Point point =
                // measurement is device name/id
                Point.measurement(metric.getDevice())
                        // name is metric name
                        .addTag("name", metric.getName())
                        // value is metric value
                        .addField("value", metric.getValue())
                        .time(metric.getTimestamp(), WritePrecision.NS);
        writeApi.writePoint(bucket, org, point);
    }
}
