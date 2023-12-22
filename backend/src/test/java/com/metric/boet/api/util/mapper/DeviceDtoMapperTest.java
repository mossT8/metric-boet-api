package com.metric.boet.api.util.mapper;

import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.service.mapper.EntityDtoVisitor;
import com.metric.boet.api.service.mapper.visitors.UserDtoMapperVisitor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceDtoMapperTest implements EntityDtoVisitor<Device, DeviceDto> {

    @Autowired
    UserDtoMapperVisitor userDtoMapperVisitor;

    @Override
    public DeviceDto visit(Device request) {
        DeviceDto networkSafeObject = new DeviceDto();

        networkSafeObject.setUuid(request.getUuid());
        networkSafeObject.setName(request.getName());
        networkSafeObject.setType(request.getType());
        networkSafeObject.setLocation(request.getLocation());
        networkSafeObject.setStatus(request.getStatus());
        networkSafeObject.setCreatedAt(request.getCreatedAt());
        networkSafeObject.setUpdatedAt(request.getUpdatedAt());
        networkSafeObject.setToken(request.getToken());
        networkSafeObject.setUser(userDtoMapperVisitor.visit(request.getUserCreated()));

        return networkSafeObject;
    }
}
