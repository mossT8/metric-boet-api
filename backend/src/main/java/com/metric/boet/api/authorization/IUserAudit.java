package com.metric.boet.api.authorization;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.authorization.enums.ERole;

public interface IUserAudit {
    long getUserId();
    User getUser();

    ERole getUserRole();
}
