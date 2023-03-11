package com.metric.boet.api.core.mapper.imp;

import com.metric.boet.api.core.mapper.IDtoMapper;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.entity.User;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Component;

@Component
public class UserDtoMapper implements IDtoMapper<User, UserDto> {

    @Override
    public UserDto removeSensitiveInformation(User inputObject) throws ExecutionControl.NotImplementedException {
        UserDto networkSafeObject = new UserDto();

        networkSafeObject.setUsername(inputObject.getUsername());
        networkSafeObject.setAccountCode(inputObject.getAccountCode());
        networkSafeObject.setFirstName(inputObject.getFirstName());
        networkSafeObject.setLastName(inputObject.getLastName());
        networkSafeObject.setPhone(inputObject.getPhone());
        networkSafeObject.setEmail(inputObject.getEmail());
        networkSafeObject.setCreatedAt(inputObject.getCreatedAt());
        networkSafeObject.setUpdatedAt(inputObject.getUpdatedAt());
        networkSafeObject.setRoles(inputObject.getRoles());

        return networkSafeObject;
    }
}
