package com.metric.boet.api.util.mapper;

import com.metric.boet.api.dto.RoleDto;
import com.metric.boet.api.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoMapperTest implements IDtoMapper<Role, RoleDto> {


    @Override
    public RoleDto removeSensitiveInformation(Role inputObject) {

        RoleDto censoredObject = new RoleDto();

        censoredObject.setRoleName(inputObject.getName().name());

        return censoredObject;
    }
}
