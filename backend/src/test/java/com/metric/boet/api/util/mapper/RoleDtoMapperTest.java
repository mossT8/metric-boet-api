package com.metric.boet.api.util.mapper;

import com.metric.boet.api.dto.RoleDto;
import com.metric.boet.api.entity.Role;
import com.metric.boet.api.service.mapper.EntityDtoVisitor;
import org.springframework.stereotype.Component;

@Component
public class RoleDtoMapperTest implements EntityDtoVisitor<Role, RoleDto> {

    @Override
    public RoleDto visit(Role request) {
        RoleDto censoredObject = new RoleDto();

        censoredObject.setRoleName(request.getName().name());

        return censoredObject;
    }
}
