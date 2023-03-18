package com.metric.boet.api.dto;

public class RoleDto extends BasicObjectDto {
    private String roleName;

    public RoleDto() {
    }

    public RoleDto(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
