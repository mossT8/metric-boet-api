package com.metric.boet.api.service.generator.imp;

import com.metric.boet.api.core.generator.imp.AccountCodeGenerator;
import com.metric.boet.api.core.generator.imp.DeviceUidGenerator;
import com.metric.boet.api.service.generator.ISimpleGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleGeneratorService implements ISimpleGeneratorService {

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
