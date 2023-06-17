package com.metric.boet.api.entity;

import com.metric.boet.api.authorization.IUserAudit;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;

@MappedSuperclass
public abstract class BasicDataBean {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    protected Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_created_id", nullable = false)
    private User user;

    @NotNull
    @PositiveOrZero
    protected int userCreatedTypeId = 0;

    protected BasicDataBean() {
        Date createDate = new Date();
        this.createdAt = new Date(createDate.getTime());
        this.updatedAt = new Date(createDate.getTime());
    }

    public BasicDataBean(IUserAudit userAudit) {
        this();
        this.user = userAudit.getUser();
        this.userCreatedTypeId = userAudit.getUserRole().ordinal();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getUserCreatedTypeId() {
        return userCreatedTypeId;
    }

    public void setUserCreatedTypeId(int userCreatedTypeId) {
        this.userCreatedTypeId = userCreatedTypeId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BasicDataBean other = (BasicDataBean) obj;
        if (id != other.id)
            return false;
        return true;
    }
}