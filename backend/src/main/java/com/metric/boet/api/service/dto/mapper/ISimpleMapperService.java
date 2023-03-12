package com.metric.boet.api.service.dto.mapper;

import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.User;
import jdk.jshell.spi.ExecutionControl;

import java.util.*;

public interface ISimpleMapperService {
    DeviceDto getDeviceDto(Device device) throws ExecutionControl.NotImplementedException;

    List<DeviceDto> getDevicesDto(List<Device> devices) throws ExecutionControl.NotImplementedException;

    UserDto getUserDto(User user) throws ExecutionControl.NotImplementedException;

    List<UserDto> getUsersDto(List<User> users) throws ExecutionControl.NotImplementedException;

    HtmlPageDto getHtmlPageDto(HtmlPage htmlPage) throws ExecutionControl.NotImplementedException;

    List<HtmlPageDto> getHtmlPagesDto(List<HtmlPage> devices) throws ExecutionControl.NotImplementedException;

}
