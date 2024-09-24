package com.metric.boet.api.util.repo.bean;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.dto.BasicObjectDto;
import com.metric.boet.api.entity.User;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@MappedSuperclass
public abstract class AbstractTrackedDataBean<DTO extends BasicObjectDto> extends AbstractDataBean{
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
    @JoinColumn(name = "user_created_id")
    private User userCreated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_updated_id")
    private User lastUpdatedUser;

    public abstract DTO mapToDTO();

    protected AbstractTrackedDataBean() {
        Date createDate = new Date();
        this.createdAt = new Date(createDate.getTime());
        this.updatedAt = new Date(createDate.getTime());
    }

    public AbstractTrackedDataBean(IUserAudit userAudit) {
        this();
        this.lastUpdatedUser = userAudit.getUser();
        this.userCreated = userAudit.getUser();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public User getUserCreated() {
        return userCreated;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

    public User getLastUpdatedUser() {
        return lastUpdatedUser;
    }

    public void setLastUpdatedUser(User lastUpdatedUser) {
        this.lastUpdatedUser = lastUpdatedUser;
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
        AbstractTrackedDataBean other = (AbstractTrackedDataBean) obj;
        if (id != other.id)
            return false;
        return true;
    }
}