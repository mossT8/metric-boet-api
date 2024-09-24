package com.metric.boet.api.util.service;

import com.metric.boet.api.dto.BasicObjectDto;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class AbstractDataBeanService<REQUEST_BEAN_CLASS extends AbstractWebAppEndpointPayload, DATABASE_BEAN_CLASS extends AbstractDataBean>
        implements IAbstractDataBeanService<DATABASE_BEAN_CLASS>, ICRUDAbstractDataBeanService<REQUEST_BEAN_CLASS> {
    @Override
    public BasicAPIResponse findResponseById(Long id) {
        DATABASE_BEAN_CLASS bean = findBeanById(id);
        BasicAPIResponse response = new BasicAPIResponse();
        response.setData(bean.mapToDTO());
        return response;
    }

    @Override
    public BasicAPIResponse findResponseByCreatedDate(Date createdAt) {
        List<DATABASE_BEAN_CLASS> beans = findBeanByCreatedDate(createdAt);
        List<BasicObjectDto> mappedBeans = new ArrayList<>();
        BasicAPIResponse response = new BasicAPIResponse();
        for (DATABASE_BEAN_CLASS bean: beans) {
            mappedBeans.add(bean.mapToDTO());
        }
        response.setData(mappedBeans);
        return response;
    }

    @Override
    public BasicAPIResponse findResponseByUpdatedDate(Date updatedAt) {
        List<DATABASE_BEAN_CLASS> beans = findBeanByUpdatedDate(updatedAt);
        List<BasicObjectDto> mappedBeans = new ArrayList<>();
        BasicAPIResponse response = new BasicAPIResponse();
        for (DATABASE_BEAN_CLASS bean: beans) {
            mappedBeans.add(bean.mapToDTO());
        }
        response.setData(mappedBeans);
        return response;
    }
}
