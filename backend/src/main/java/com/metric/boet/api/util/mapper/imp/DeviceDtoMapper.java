package com.metric.boet.api.util.mapper.imp;

import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.util.mapper.IDtoMapper;
import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceDtoMapper implements IDtoMapper<Device, DeviceDto> {

    @Autowired
    UserDtoMapper userDtoMapper;

    @Autowired
    UserRepository userRepository;

    @Override
    public DeviceDto removeSensitiveInformation(Device inputObject) {
        DeviceDto networkSafeObject = new DeviceDto();

        networkSafeObject.setUuid(inputObject.getUuid());
        networkSafeObject.setName(inputObject.getName());
        networkSafeObject.setType(inputObject.getType());
        networkSafeObject.setLocation(inputObject.getLocation());
        networkSafeObject.setStatus(inputObject.getStatus());
        networkSafeObject.setCreatedAt(inputObject.getCreatedAt());
        networkSafeObject.setUpdatedAt(inputObject.getUpdatedAt());
        networkSafeObject.setToken(inputObject.getToken());
        networkSafeObject.setUser(userDtoMapper.removeSensitiveInformation(inputObject.getUserCreated()));

        return networkSafeObject;
    }
}