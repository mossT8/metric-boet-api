package com.metric.boet.api.util.service;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;

import java.util.Date;

public interface ICRUDAbstractTrackedDataBeanService<REQUEST_BEAN_CLASS extends AbstractWebAppEndpointPayload> {
    default AbstractTrackedDataBean updateLastUpdate(AbstractTrackedDataBean abstractDataBean, IUserAudit userAudit) {
        abstractDataBean.setUpdatedAt(new Date());
        abstractDataBean.setLastUpdatedUser(userAudit.getUser());

        return abstractDataBean;
    }

    BasicAPIResponse create(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse replace(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse update(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse delete(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);
}