package com.metric.boet.api.util.api;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.service.beans.UserServiceTracked;
import com.metric.boet.api.util.NullUtil;

public class ApiUtil {
    public static boolean DoesUserBeanBelongToUser(UserServiceTracked userService, User userDoingRequest, KeyApiRequestAbstract payload) {
        User userResponse = null;
        try {
            userResponse = userService.findBeanByAccountCode(payload.getKey());
        } catch (Exception e) {
           userResponse = null;
        }
        if (NullUtil.isNull(userResponse)) {
            try {
                userResponse = userService.findBeanByUsername(payload.getKey());
            } catch (Exception e) {
                userResponse = null;
            }
        }

        return NullUtil.isNotNull(userResponse) && userResponse.getUserCreated().equals(userDoingRequest);
    }
}
