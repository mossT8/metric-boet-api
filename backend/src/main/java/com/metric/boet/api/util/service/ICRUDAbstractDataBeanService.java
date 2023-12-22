package com.metric.boet.api.util.service;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;

import java.util.Date;

public interface ICRUDAbstractDataBeanService<REQUEST_BEAN_CLASS extends AbstractWebAppEndpointPayload> {
    default AbstractDataBean updateLastUpdate(AbstractDataBean abstractDataBean, IUserAudit userAudit) {
        abstractDataBean.setUpdatedAt(new Date());
        abstractDataBean.setLastUpdatedUser(userAudit.getUser());

        return abstractDataBean;
    }

    BasicAPIResponse create(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse replace(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse update(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse delete(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);
}