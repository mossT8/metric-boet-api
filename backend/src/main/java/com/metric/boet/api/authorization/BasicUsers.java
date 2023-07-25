package com.metric.boet.api.authorization;

import com.metric.boet.api.authorization.enums.ERole;
import com.metric.boet.api.entity.User;

public class BasicUsers {
    private static long ADMIN_ID = 1;
    private static long SYSTEM_AUTO_ID = 2;

    private static String ADMIN_CODE = "A001";
    private static String SYSTEM_AUTO_CODE = "A002";

    private static ERole ADMIN_ROLE = ERole.ROLE_ADMIN;
    private static ERole SYSTEM_AUTO_ROLE = ERole.ROLE_SYSTEM_AUTO;

    public static IUserAudit ADMIN_AUDIT = new IUserAudit() {
        @Override
        public long getUserId() {
            return ADMIN_ID;
        }

        @Override
        public User getUser() {
            return BasicUsers.ADMIN_USER;
        }

        @Override
        public ERole getUserRole() {
            return ADMIN_ROLE;
        }
    };

    public static IUserAudit SYSTEM_AUTO_AUDIT = new IUserAudit() {
        @Override
        public long getUserId() {
            return ADMIN_ID;
        }

        @Override
        public User getUser() {
            return BasicUsers.ADMIN_USER;
        }

        @Override
        public ERole getUserRole() {
            return SYSTEM_AUTO_ROLE;
        }
    };

    public static User ADMIN_USER;

    static {
        BasicUsers.ADMIN_USER = new User(ADMIN_CODE, "Admin", "Admin", "NA", "Admin", "tannar.moss@gmail.com", "$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq", ADMIN_AUDIT);
        BasicUsers.ADMIN_USER.setId(ADMIN_ID); // hard coded

        BasicUsers.SYSTEM_AUTO_AUDIT = new User(SYSTEM_AUTO_CODE, "System", "Auto", "NA", "SystemAuto", "system.auto@gmail.com", "$2a$10$wyRxHWfDWNB4mRT0Rb8czO7Oq4PCYaI3HLb8zGvppijzpS0oRXsiq", SYSTEM_AUTO_AUDIT);
        BasicUsers.ADMIN_USER.setId(SYSTEM_AUTO_ID); // hard coded

    }
}
