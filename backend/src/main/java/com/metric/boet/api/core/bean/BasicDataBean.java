package com.metric.boet.api.core.bean;

import com.metric.boet.api.core.authorization.IUserAudit;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.Date;


public abstract class BasicDataBean {
    // note each @entity requires an @id so why we exclude here
    public long id;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    public Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    public Date updatedAt;

    @NotNull
    @PositiveOrZero
    public long userCreatedId = 0;

    @NotNull
    @PositiveOrZero
    public int userCreatedTypeId = 0;

    public BasicDataBean(IUserAudit userAudit) {
        this.userCreatedId = userAudit.getUserId();
        this.userCreatedTypeId = userAudit.getUserRole().ordinal();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserCreatedId() {
        return userCreatedId;
    }

    public void setUserCreatedId(long userCreatedId) {
        this.userCreatedId = userCreatedId;
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