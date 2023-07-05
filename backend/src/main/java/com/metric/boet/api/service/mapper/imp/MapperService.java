package com.metric.boet.api.service.mapper.imp;

import com.metric.boet.api.util.mapper.imp.DeviceDtoMapper;
import com.metric.boet.api.util.mapper.imp.HtmlPageDtoMapper;
import com.metric.boet.api.util.mapper.imp.RoleDtoMapper;
import com.metric.boet.api.util.mapper.imp.UserDtoMapper;
import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.dto.RoleDto;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.Role;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.service.mapper.IMapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService implements IMapperService {
    // mappers
    @Autowired
    DeviceDtoMapper deviceDtoMapper;

    @Autowired
    UserDtoMapper userDtoMapper;

    @Autowired
    HtmlPageDtoMapper htmlPageDtoMapper;

    @Autowired
    RoleDtoMapper roleDtoMapper;

    // mapping logic
    @Override
    public DeviceDto getDeviceDto(Device device) {
        return deviceDtoMapper.removeSensitiveInformation(device);
    }

    @Override
    public List<DeviceDto> getDevicesDto(List<Device> devices) {
        List<DeviceDto> mappedDevices = new ArrayList<>();
        for (Device device : devices) {
            mappedDevices.add(getDeviceDto(device));
        }

        return mappedDevices;
    }

    @Override
    public UserDto getUserDto(User user) {
        return userDtoMapper.removeSensitiveInformation(user);
    }

    @Override
    public List<UserDto> getUsersDto(List<User> users) {
        List<UserDto> mappedUsers = new ArrayList<>();
        for (User user : users) {
            mappedUsers.add(getUserDto(user));
        }

        return mappedUsers;
    }

    @Override
    public HtmlPageDto getHtmlPageDto(HtmlPage htmlPage) {
        return htmlPageDtoMapper.removeSensitiveInformation(htmlPage);
    }

    @Override
    public List<HtmlPageDto> getHtmlPagesDto(List<HtmlPage> pages) {
        List<HtmlPageDto> mappedUsers = new ArrayList<>();
        for (HtmlPage page : pages) {
            mappedUsers.add(getHtmlPageDto(page));
        }

        return mappedUsers;
    }

    @Override
    public RoleDto getRoleDto(Role role) {
        return roleDtoMapper.removeSensitiveInformation(role);
    }

    @Override
    public List<RoleDto> getRolesDto(List<Role> roles) {
        List<RoleDto> mappedRoles = new ArrayList<>();
        for (Role role : roles) {
            mappedRoles.add(getRoleDto(role));
        }

        return mappedRoles;
    }
}
