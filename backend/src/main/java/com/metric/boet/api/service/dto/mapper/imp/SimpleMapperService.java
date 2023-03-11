package com.metric.boet.api.service.dto.mapper.imp;

import com.metric.boet.api.core.mapper.imp.DeviceDtoMapper;
import com.metric.boet.api.core.mapper.imp.UserDtoMapper;
import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.service.dto.mapper.ISimpleMapperService;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleMapperService implements ISimpleMapperService {
    @Autowired
    DeviceDtoMapper deviceDtoMapper;

    @Autowired
    UserDtoMapper userDtoMapper;

    @Override
    public DeviceDto getDeviceDto(Device device) throws ExecutionControl.NotImplementedException {
        return deviceDtoMapper.removeSensitiveInformation(device);
    }

    @Override
    public List<DeviceDto> getDevicesDto(List<Device> devices) throws ExecutionControl.NotImplementedException {
        List<DeviceDto> mappedDevices = new ArrayList<>();
        for (Device device : devices) {
            mappedDevices.add(getDeviceDto(device));
        }

        return mappedDevices;
    }

    @Override
    public UserDto getUserDto(User user) throws ExecutionControl.NotImplementedException {
        return userDtoMapper.removeSensitiveInformation(user);
    }

    @Override
    public List<UserDto> getUsersDto(List<User> users) throws ExecutionControl.NotImplementedException {
        List<UserDto> mappedDevices = new ArrayList<>();
        for (User user : users) {
            mappedDevices.add(getUserDto(user));
        }

        return mappedDevices;
    }
}
