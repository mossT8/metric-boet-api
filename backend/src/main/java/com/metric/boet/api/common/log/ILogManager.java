package com.metric.boet.api.common.log;

public interface ILogManager {
    void logInfo(String message);
    void logDebug(String message);
    void logError(String message);
    void logWarn(String message);

}
