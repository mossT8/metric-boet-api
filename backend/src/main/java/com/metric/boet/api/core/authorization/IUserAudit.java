package com.metric.boet.api.core.authorization;

import com.metric.boet.api.entity.enums.ERole;

public interface IUserAudit {
    long getUserId();

    ERole getUserRole();
}
