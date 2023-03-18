package com.metric.boet.api.service.dto.mapper;

import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.dto.RoleDto;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.Role;
import com.metric.boet.api.entity.User;

import java.util.*;

public interface ISimpleMapperService {
    // device mappings
    DeviceDto getDeviceDto(Device device);

    List<DeviceDto> getDevicesDto(List<Device> devices);

    // user mappings
    UserDto getUserDto(User user);

    List<UserDto> getUsersDto(List<User> users);

    // html page mappings
    HtmlPageDto getHtmlPageDto(HtmlPage htmlPage);

    List<HtmlPageDto> getHtmlPagesDto(List<HtmlPage> devices);

    // role mappings
    RoleDto getRoleDto(Role role);

    List<RoleDto> getRolesDto(List<Role> roles);

}
