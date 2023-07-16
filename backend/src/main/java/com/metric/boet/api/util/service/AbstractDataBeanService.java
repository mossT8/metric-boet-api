package com.metric.boet.api.util.service;

import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.entity.Role;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.service.mapper.imp.MapperService;
import com.metric.boet.api.util.NullUtil;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static com.metric.boet.api.authorization.enums.ERole.ROLE_ADMIN;

public abstract class AbstractDataBeanService<REQUEST_BEAN_CLASS extends AbstractWebAppEndpointPayload>
        implements IAbstractDataBeanService, ICRUDAbstractDataBeanService<REQUEST_BEAN_CLASS> {
    @Autowired
    protected MapperService mapperService;

    private void updateLastUpdated(AbstractDataBean updatingBean, User user) {
        updatingBean.setUpdatedAt(new Date());
        updatingBean.setLastUpdatedUser(user);
    }

    protected void updateLastUpdated(AbstractDataBean updatingBean, IUserAudit userAudit) {
        updateLastUpdated(updatingBean, userAudit.getUser());
    }

    public boolean doesBelongToUser(AbstractDataBean beanInQuestion, User user) {
        if (NullUtil.isNotNull(beanInQuestion)) {
            return user.getUserRole().equals(ROLE_ADMIN) || beanInQuestion.getUserCreated().equals(user);
        }
        return false;
    }

    public static BasicAPIResponse getNegativeResponse() {
        return new BasicAPIResponse("Error: could not resolve request. Please contact system support if this error persist", false);
    }
}
