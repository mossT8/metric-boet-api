package com.metric.boet.api.util.mapper;

import com.metric.boet.api.dto.RoleDto;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.service.mapper.EntityDtoVisitor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class UserDtoMapperTest implements EntityDtoVisitor<User, UserDto> {

    @Override
    public UserDto visit(User request) {
        UserDto networkSafeObject = new UserDto();

        networkSafeObject.setUsername(request.getUsername());
        networkSafeObject.setAccountCode(request.getAccountCode());
        networkSafeObject.setFirstName(request.getFirstName());
        networkSafeObject.setLastName(request.getLastName());
        networkSafeObject.setPhone(request.getPhone());
        networkSafeObject.setEmail(request.getEmail());
        networkSafeObject.setCreatedAt(request.getCreatedAt());
        networkSafeObject.setUpdatedAt(request.getUpdatedAt());
        Set<RoleDto> rolesMapped = new HashSet<>();
        request.getRoles().forEach(role -> rolesMapped.add(role.mapToDTO()));
        networkSafeObject.setRoles(rolesMapped);

        return networkSafeObject;
    }
}
