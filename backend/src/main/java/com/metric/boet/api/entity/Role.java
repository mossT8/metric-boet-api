package com.metric.boet.api.entity;

import com.metric.boet.api.core.authorization.BasicUsers;
import com.metric.boet.api.core.bean.BasicDataBean;
import com.metric.boet.api.entity.enums.ERole;

import javax.persistence.*;

@Entity
@Table(name = "roles",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
        })
public class Role extends BasicDataBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Role() {
        super(BasicUsers.ADMIN_USER);
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