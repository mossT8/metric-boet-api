package com.metric.boet.api.util.service;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.response.BasicAPIResponse;

import java.util.Date;

public interface IAbstractDataBeanService {
    BasicAPIResponse findById(Long id);
    BasicAPIResponse findByCreatedDate(Date createdAt);
    BasicAPIResponse findByUpdatedDate(Date updatedAt);
    BasicAPIResponse findByCreatedUserId(Long userId);
    BasicAPIResponse findByCreatedUser(User user);
    BasicAPIResponse findByUpdatedUserId(Long userId);
    BasicAPIResponse findByUpdatedUser(User user);
}
