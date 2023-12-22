package com.javatpoint.common.elastic;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.IndexResponse;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class ElasticSearchService {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticSearchService.class);

    private static final String LOG_INDEX_KEY = "logs";
    private final boolean printOutElasticIds = false;

    private ElasticsearchClient client;

    @Autowired
    public ElasticSearchService(ElasticsearchClient client) {
        this.client = client;
    }

    public void sendLogViaAPI(String level, String message) {
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        String jsonString = "{\"timestamp\":\"" + timestamp + "\",\"level\":\"" + level + "\",\"message\":\"" + message + "\"}";
        System.out.println(jsonString);
        try {
            IndexResponse response = client.index(i -> i
                    .index(LOG_INDEX_KEY)
                    .withJson(new StringReader(jsonString))
            );

            if (printOutElasticIds) {
                String index = response.index();
                String id = response.id();
                System.out.println("Index: " + index + ", Id: " + id);
            }
        } catch (IOException e) {
            LOG.error("Failed to send log message via API: " + e.getMessage() + " and request body " + jsonString);
            e.printStackTrace();
        }
    }
}
