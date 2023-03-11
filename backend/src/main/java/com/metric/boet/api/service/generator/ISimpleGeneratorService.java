package com.metric.boet.api.service.generator;

public interface ISimpleGeneratorService {
    String getNextUserAccountCode();
    String getCurrentMaxUserAccountCode();
    String getNextDeviceUid();
}
