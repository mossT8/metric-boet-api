package com.metric.boet.api.service.mapper.imp;

import com.metric.boet.api.dto.*;
import com.metric.boet.api.service.mapper.EntityDtoVisitor;
import com.metric.boet.api.service.mapper.visitors.DeviceDtoMapperVisitor;
import com.metric.boet.api.service.mapper.visitors.HtmlPageDtoMapperVisitor;
import com.metric.boet.api.service.mapper.visitors.RoleDtoMapperVisitor;
import com.metric.boet.api.service.mapper.visitors.UserDtoMapperVisitor;
import com.metric.boet.api.entity.Device;
import com.metric.boet.api.entity.HtmlPage;
import com.metric.boet.api.entity.Role;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.service.mapper.IMapperService;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService implements IMapperService {
    // mappers
    @Autowired
    DeviceDtoMapperVisitor deviceDtoMapperVisitor;

    @Autowired
    UserDtoMapperVisitor userDtoMapperVisitor;

    @Autowired
    HtmlPageDtoMapperVisitor htmlPageDtoMapperVisitor;

    @Autowired
    RoleDtoMapperVisitor roleDtoMapperVisitor;

    // Define a generic mapping method that uses the visitor pattern
    private <Entity extends AbstractDataBean, Dto extends BasicObjectDto> Dto mapEntityToDto(Entity entity, EntityDtoVisitor<Entity, Dto> visitor) {
        return visitor.visit(entity);
    }

    // mapping logic
    @Override
    public DeviceDto getDeviceDto(Device device) {
        return mapEntityToDto(device, deviceDtoMapperVisitor);
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
        return mapEntityToDto(user, userDtoMapperVisitor);
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
        return mapEntityToDto(htmlPage, htmlPageDtoMapperVisitor);
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
        return mapEntityToDto(role, roleDtoMapperVisitor);
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
