package com.metric.boet.api.util.service;

import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

public interface IAbstractDataBeanService<BEAN_CLASS extends AbstractDataBean> {
    BEAN_CLASS findBeanById(Long id) throws NoSuchElementException;

    List<BEAN_CLASS> findBeanByCreatedDate(Date createdAt);

    List<BEAN_CLASS> findBeanByUpdatedDate(Date updatedAt);

    BasicAPIResponse findResponseById(Long id);

    BasicAPIResponse findResponseByCreatedDate(Date createdAt);

    BasicAPIResponse findResponseByUpdatedDate(Date updatedAt);
}
