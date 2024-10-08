package com.metric.boet.api.entity;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.authorization.IUserAudit;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.service.mapper.visitors.HtmlPageDtoMapperVisitor;
import com.metric.boet.api.util.repo.bean.AbstractTrackedDataBean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "html_pages",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "url")
        })
public class HtmlPage extends AbstractTrackedDataBean<HtmlPageDto> {
    @Column(nullable = false)
    private String url;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String html;

    @Column
    private Boolean visibleOnNav;

    @Column
    private Boolean visibleForUsers;

    @Column
    private Boolean visibleForModerators;

    @Override
    public HtmlPageDto mapToDTO() {
        HtmlPageDtoMapperVisitor mapperVisitor = new HtmlPageDtoMapperVisitor();
        return mapperVisitor.visit(this);
    }

    public HtmlPage() {
        super(BasicUsers.SYSTEM_AUTO_AUDIT);
        this.visibleForUsers = true;
        this.visibleForModerators = true;
    }

    public HtmlPage(IUserAudit userAudit) {
        super(userAudit);
        this.visibleForUsers = true;
        this.visibleForModerators = true;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public Boolean getVisibleForUsers() {
        return visibleForUsers;
    }

    public void setVisibleForUsers(Boolean visibleForUsers) {
        this.visibleForUsers = visibleForUsers;
    }

    public Boolean getVisibleForModerators() {
        return visibleForModerators;
    }

    public void setVisibleForModerators(Boolean visibleForFarmers) {
        this.visibleForModerators = visibleForFarmers;
    }

    public Boolean getVisibleOnNav() {
        return visibleOnNav;
    }

    public void setVisibleOnNav(Boolean visibleOnNav) {
        this.visibleOnNav = visibleOnNav;
    }
}
