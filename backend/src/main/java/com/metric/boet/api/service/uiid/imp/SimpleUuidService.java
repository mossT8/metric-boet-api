package com.metric.boet.api.service.uiid.imp;

import com.metric.boet.api.util.uuid.imp.AccountCodeGenerator;
import com.metric.boet.api.util.uuid.imp.DeviceUidGenerator;
import com.metric.boet.api.service.uiid.ISimpleUuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUuidService implements ISimpleUuidService {

    @Autowired
    DeviceUidGenerator deviceUidGenerator;

    @Autowired
    AccountCodeGenerator accountCodeGenerator;

    @Override
    public String getNextUserAccountCode() {
        return accountCodeGenerator.getNextUnique();
    }

    @Override
    public String getCurrentMaxUserAccountCode() {
        return accountCodeGenerator.getNextUnique();
    }

    @Override
    public String getNextDeviceUid() {
        return deviceUidGenerator.getNextUnique();
    }
}
