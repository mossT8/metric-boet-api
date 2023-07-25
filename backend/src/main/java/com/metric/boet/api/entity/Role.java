package com.metric.boet.api.entity;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.authorization.enums.ERole;
import com.metric.boet.api.dto.RoleDto;
import com.metric.boet.api.service.mapper.visitors.RoleDtoMapperVisitor;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;

import javax.persistence.*;

@Entity
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
public class Role extends AbstractDataBean<RoleDto> {
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @Override
    public RoleDto mapToDTO() {
        RoleDtoMapperVisitor  mapperVisitor = new RoleDtoMapperVisitor();
        return mapperVisitor.visit(this);
    }

    public Role() {
        super(BasicUsers.ADMIN_AUDIT);
    }

    public Role(ERole name, User user) {
        super(user);
        this.name = name;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }
}