package com.metric.boet.api.util.service;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.response.BasicAPIResponse;

import java.util.Date;

public interface IAbstractDataBeanService {
    BasicAPIResponse getById(Long id);
    BasicAPIResponse getByCreatedDate(Date createdAt);
    BasicAPIResponse getByUpdatedDate(Date updatedAt);
    BasicAPIResponse getByCreatedUserId(Long userId);
    BasicAPIResponse getByCreatedUser(User user);
    BasicAPIResponse getByUpdatedUserId(Long userId);
    BasicAPIResponse getByUpdatedUser(User user);
}
