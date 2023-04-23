package com.metric.boet.api.core.authorization;

import com.metric.boet.api.entity.enums.ERole;
import com.metric.boet.api.entity.User;

public class BasicUsers {
    private static long ADMIN_ID = 1;
    private static ERole ADMIN_ROLE = ERole.ROLE_ADMIN;

    private static IUserAudit ADMIN_AUDIT = new IUserAudit() {
        @Override
        public long getUserId() {
            return ADMIN_ID;
        }

        @Override
        public ERole getUserRole() {
            return ADMIN_ROLE;
        }
    };

    public static User ADMIN_USER = new User("A1", "Admin", "Admin", "NA", "Admin", "tannar.moss@gmail.com", "$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq", ADMIN_AUDIT);
}