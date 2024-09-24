package com.metric.boet.api.util.service;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.dto.BasicObjectDto;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.NullUtil;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.metric.boet.api.authorization.enums.ERole.ROLE_ADMIN;

public abstract class AbstractTrackedDataBeanService<REQUEST_BEAN_CLASS extends AbstractWebAppEndpointPayload, DATABASE_BEAN_CLASS extends AbstractTrackedDataBean>
        implements IAbstractTrackedDataBeanService<DATABASE_BEAN_CLASS>, ICRUDAbstractTrackedDataBeanService<REQUEST_BEAN_CLASS> {
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

    @Override
    public BasicAPIResponse findResponseByCreatedUserId(Long userId) {
        List<DATABASE_BEAN_CLASS> beans = findBeanByCreatedUserId(userId);
        List<BasicObjectDto> mappedBeans = new ArrayList<>();
        BasicAPIResponse response = new BasicAPIResponse();
        for (DATABASE_BEAN_CLASS bean: beans) {
            mappedBeans.add(bean.mapToDTO());
        }
        response.setData(mappedBeans);
        return response;
    }

    @Override
    public BasicAPIResponse findResponseByCreatedUser(User user) {
        List<DATABASE_BEAN_CLASS> beans = findBeanByCreatedUser(user);
        List<BasicObjectDto> mappedBeans = new ArrayList<>();
        BasicAPIResponse response = new BasicAPIResponse();
        for (DATABASE_BEAN_CLASS bean: beans) {
            mappedBeans.add(bean.mapToDTO());
        }
        response.setData(mappedBeans);
        return response;
    }

    @Override
    public BasicAPIResponse findResponseByUpdatedUserId(Long userId) {
        List<DATABASE_BEAN_CLASS> beans = findBeanByUpdatedUserId(userId);
        List<BasicObjectDto> mappedBeans = new ArrayList<>();
        BasicAPIResponse response = new BasicAPIResponse();
        for (DATABASE_BEAN_CLASS bean: beans) {
            mappedBeans.add(bean.mapToDTO());
        }
        response.setData(mappedBeans);
        return response;
    }

    @Override
    public BasicAPIResponse findResponseByUpdatedUser(User user) {
        List<DATABASE_BEAN_CLASS> beans = findBeanByUpdatedUser(user);
        List<BasicObjectDto> mappedBeans = new ArrayList<>();
        BasicAPIResponse response = new BasicAPIResponse();
        for (DATABASE_BEAN_CLASS bean: beans) {
            mappedBeans.add(bean.mapToDTO());
        }
        response.setData(mappedBeans);
        return response;
    }

    private void updateLastUpdated(AbstractTrackedDataBean updatingBean, User user) {
        updatingBean.setUpdatedAt(new Date());
        updatingBean.setLastUpdatedUser(user);
    }

    protected void updateLastUpdated(AbstractTrackedDataBean updatingBean, IUserAudit userAudit) {
        updateLastUpdated(updatingBean, userAudit.getUser());
    }

    public boolean doesBelongToUser(AbstractTrackedDataBean beanInQuestion, User user) {
        if (NullUtil.isNotNull(beanInQuestion)) {
            return user.getUserRole().equals(ROLE_ADMIN) || beanInQuestion.getUserCreated().equals(user);
        }
        return false;
    }

    public static BasicAPIResponse getNegativeResponse() {
        return new BasicAPIResponse("Error: could not resolve request. Please contact system support if this error persist", false);
    }
}
