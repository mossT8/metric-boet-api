package com.javatpoint.common.influxdb;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.WriteApiBlocking;
import com.influxdb.client.domain.BucketRetentionRules;
import com.influxdb.client.domain.WritePrecision;
import com.influxdb.client.write.Point;
import com.javatpoint.models.Metric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import javax.validation.constraints.Null;

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
        
        try {
            BucketRetentionRules retention = new BucketRetentionRules();
            retention.setEverySeconds(3600);
            influxDBClient.getBucketsApi().createBucket(bucket, retention, org);
        } catch (Exception e) {
            // proberbly there already or org doesnt exist
        }
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
