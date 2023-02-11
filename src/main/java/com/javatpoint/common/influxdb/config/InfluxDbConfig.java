package com.javatpoint.common.influxdb.config;

import com.influxdb.client.InfluxDBClient;
import com.influxdb.client.InfluxDBClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDbConfig {
    // You can generate an API token from the "API Tokens Tab" in the UI

    @Value("${influxdb.token:aldppLTVdL-edqywyrLz2HPNp5njEBy7H-ZXTTD4qItblxElfd6gGDN8cH8iqnf0iQEY8hkPEI-CitEx1EYHBw==}")
    private String token;

    @Value("${influxdb.host:localhost}")
    private String host;

    @Value("${influxdb.port:8086}")
    private int port;

    @Value("${influxdb.protocol:http}")
    private String protocol;

    @Value("${influxdb.username:admin}")
    private String userName;

    @Value("${influxdb.password:admin123}")
    private String password;

    @Bean
    public InfluxDBClient influxDBClient() {
        return InfluxDBClientFactory.create("http://localhost:8086", token.toCharArray());
    }

}
