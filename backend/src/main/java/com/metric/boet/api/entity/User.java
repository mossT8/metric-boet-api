package com.metric.boet.api.entity;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.authorization.enums.ERole;
import com.metric.boet.api.dto.UserDto;
import com.metric.boet.api.service.mapper.visitors.UserDtoMapperVisitor;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "accountCode"),
        })
public class User extends AbstractTrackedDataBean<UserDto> implements IUserAudit {
    @NotBlank
    @Size(max = 50)
    private String accountCode;

    @NotBlank
    @Size(max = 50)
    private String firstName;

    @NotBlank
    @Size(max = 50)
    private String lastName;

    @NotBlank
    @Size(max = 50)
    private String phone;

    @NotBlank
    @Size(max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public UserDto mapToDTO() {
        UserDtoMapperVisitor mapperVisitor = new UserDtoMapperVisitor();
        return mapperVisitor.visit(this);
    }

    public User() {
        super(BasicUsers.SYSTEM_AUTO_AUDIT);
    }


    public User(IUserAudit userAudit) {
        super(userAudit);
    }

    public User(String accountCode, String firstName, String lastName, String phone, String username, String email, String password, IUserAudit userAudit) {
        super(userAudit);
        this.accountCode = accountCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public IUserAudit getAudit() {
        return this;
    }

    @Override
    public long getUserId() {
        return id;
    }

    @Override
    public User getUser() {
        return this;
    }

    @Override
    public ERole getUserRole() {
        ERole currentMaxPrivilege = ERole.ROLE_USER;
        ERole currentPrivilege;

        for (Role role : roles) {
            currentPrivilege = role.getName();
            if (currentPrivilege.ordinal() < currentMaxPrivilege.ordinal()) {
                // as admin is 0
                currentMaxPrivilege = currentPrivilege;
            }
        }

        return currentMaxPrivilege;
    }
}
