package com.metric.boet.api.util.service;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public interface IAbstractTrackedDataBeanService<BEAN_CLASS extends AbstractTrackedDataBean> {
    BEAN_CLASS findBeanById(Long id) throws NoSuchElementException;

    List<BEAN_CLASS> findBeanByCreatedDate(Date createdAt);

    List<BEAN_CLASS> findBeanByUpdatedDate(Date updatedAt);

    List<BEAN_CLASS> findBeanByCreatedUserId(Long userId);

    List<BEAN_CLASS> findBeanByCreatedUser(User user);

    List<BEAN_CLASS> findBeanByUpdatedUserId(Long userId);

    List<BEAN_CLASS> findBeanByUpdatedUser(User user);

    BasicAPIResponse findResponseById(Long id);

    BasicAPIResponse findResponseByCreatedDate(Date createdAt);

    BasicAPIResponse findResponseByUpdatedDate(Date updatedAt);

    BasicAPIResponse findResponseByCreatedUserId(Long userId);

    BasicAPIResponse findResponseByCreatedUser(User user);

    BasicAPIResponse findResponseByUpdatedUserId(Long userId);

    BasicAPIResponse findResponseByUpdatedUser(User user);
}
