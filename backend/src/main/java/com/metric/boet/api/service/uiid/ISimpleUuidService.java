package com.metric.boet.api.service.uiid;

public interface ISimpleUuidService {
    String getNextUserAccountCode();
    String getCurrentMaxUserAccountCode();
    String getNextDeviceUid();
}
