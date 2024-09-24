package com.metric.boet.api.util.service;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;

public interface ICRUDAbstractDataBeanService<REQUEST_BEAN_CLASS extends AbstractWebAppEndpointPayload> {

    BasicAPIResponse create(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse replace(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse update(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);

    BasicAPIResponse delete(REQUEST_BEAN_CLASS requestBean, IUserAudit userAudit);
}