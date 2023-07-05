package com.metric.boet.api.entity;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.util.repo.bean.AbstractDataBean;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "html_pages",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "url")
        })
public class HtmlPage extends AbstractDataBean {
    @Column(nullable = false)
    private String url;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String html;

    @NotBlank
    private Boolean visibleOnNav;

    @NotBlank
    private Boolean visibleForUsers;

    @NotBlank
    private Boolean visibleForModerators;

    public HtmlPage() {
        super(BasicUsers.ADMIN_AUDIT);
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
