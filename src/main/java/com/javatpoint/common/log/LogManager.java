package com.javatpoint.common.log;

import com.javatpoint.common.elastic.ElasticSearchService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogManager implements InitializingBean, DisposableBean {

    private final ElasticSearchService elasticSearchService;

    @Autowired
    public LogManager(ElasticSearchService elasticSearchService) {
        this.elasticSearchService = elasticSearchService;
    }

    private static final Logger LOG = LoggerFactory.getLogger(LogManager.class);

    @Value("${elasticsearch.forceSendViaAPICall:false}")
    private boolean forceSendViaAPICall;
    private static final String LEVEL_INFO = "info";
    private static final String LEVEL_DEBUG = "debug";
    private static final String LEVEL_ERROR = "error";
    private static final String LEVEL_WARN = "warn";

    public void logInfo(String message) {
        logMessage(LEVEL_INFO, message);
    }

    public void logDebug(String message) {
        logMessage(LEVEL_DEBUG, message);
    }

    public void logError(String message) {
        logMessage(LEVEL_ERROR, message);
    }

    public void logWarn(String message) {
        logMessage(LEVEL_WARN, message);
    }

    public void logMessage(String level, String message) {
        if (forceSendViaAPICall) {
            elasticSearchService.sendLogViaAPI(level, message);
        }

        switch (level) {
            case LEVEL_INFO:
                LOG.info("Metric Json = " + message);
                break;
            case LEVEL_DEBUG:
                LOG.debug(message);
                break;
            case LEVEL_ERROR:
                LOG.error(message);
                break;
            case LEVEL_WARN:
                LOG.warn(message);
                break;
            default:
                LOG.info(message);
                break;
        }

    }

    @Override
    public void destroy() throws Exception {

    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
