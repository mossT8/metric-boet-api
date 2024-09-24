package com.metric.boet.api.util.repo.bean;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.dto.BasicObjectDto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDataBean<DTO extends BasicObjectDto> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;

    public abstract DTO mapToDTO();

    protected AbstractDataBean() {
    }

    public AbstractDataBean(IUserAudit userAudit) {
        this();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
        AbstractDataBean other = (AbstractDataBean) obj;
        if (id != other.id)
            return false;
        return true;
    }
}