package com.metric.boet.api.util.api;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.basic.KeyApiRequestAbstract;
import com.metric.boet.api.service.beans.UserService;
import com.metric.boet.api.util.NullUtil;

import java.util.NoSuchElementException;

public class ApiUtil {
    public static boolean DoesUserBeanBelongToUser(UserService userService, User userDoingRequest, KeyApiRequestAbstract payload) {
        User userResponse = null;
        try {
            userResponse = userService.findBeanByAccountCode(payload.getKey());
        } catch (NoSuchElementException e) {
           userResponse = null;
        }
        if (NullUtil.isNull(userResponse)) {
            try {
                userResponse = userService.findBeanByUsername(payload.getKey());
            } catch (NoSuchElementException e) {
                userResponse = null;
            }
        }

        return NullUtil.isNotNull(userResponse) && userResponse.getUserCreated().equals(userDoingRequest);
    }
}
