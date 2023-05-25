package com.metric.boet.api.entity;

import com.metric.boet.api.core.authorization.BasicUsers;
import com.metric.boet.api.core.bean.BasicDataBean;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Configurable
@Table(name = "device", uniqueConstraints = {
        @UniqueConstraint(columnNames = "uuid")
})
public class Device extends BasicDataBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_created_id", nullable = false)
    private User user;

    // overriting abstract database so that jpa can pick up createdAt for last created query
    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;

    public Device() {
        super(BasicUsers.ADMIN_USER);
    }

    public Device(String name, String uuid, String type, String location, Boolean status, String token, User user) {
        super(user);
        this.name = name;
        this.uuid = uuid;
        this.type = type;
        this.location = location;
        this.status = status;
        this.token = token;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getUuid() {
        return uuid;
    }
}

