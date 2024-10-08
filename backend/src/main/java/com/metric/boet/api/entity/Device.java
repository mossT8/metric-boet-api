package com.metric.boet.api.entity;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.dto.DeviceDto;
import com.metric.boet.api.service.mapper.visitors.DeviceDtoMapperVisitor;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;
import org.springframework.beans.factory.annotation.Configurable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Configurable
@Table(name = "device", uniqueConstraints = {
        @UniqueConstraint(columnNames = "uuid")
})
public class Device extends AbstractTrackedDataBean<DeviceDto> {

    @NotBlank
    @Size(max = 255)
    private String uuid;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String type;

    @NotBlank
    @Size(max = 255)
    private String location;

    @Column(nullable = false)
    private Boolean status;

    @NotBlank
    @Size(max = 255)
    private String token;

    @Override
    public DeviceDto mapToDTO() {
        DeviceDtoMapperVisitor mapperVisitor = new DeviceDtoMapperVisitor();
        return mapperVisitor.visit(this);
    }

    public Device() {
        super(BasicUsers.SYSTEM_AUTO_AUDIT);
    }

    public Device(String name, String uuid, String type, String location, Boolean status, String token, User user) {
        super(user);
        this.name = name;
        this.uuid = uuid;
        this.type = type;
        this.location = location;
        this.status = status;
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUuid() {
        return uuid;
    }
}

