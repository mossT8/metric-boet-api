package com.metric.boet.api.dto;

import java.util.Date;

public class HtmlPageDto extends BasicObjectDto {

    private String url;
    private String html;
    private Date createdAt;
    private Date updatedAt;
    private Boolean isVisibleForUsers;
    private Boolean isVisibleForModerators;
    private Boolean isVisibleOnNav;

    public HtmlPageDto() {
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Boolean getVisibleForUsers() {
        return isVisibleForUsers;
    }

    public void setVisibleForUsers(Boolean visibleForUsers) {
        isVisibleForUsers = visibleForUsers;
    }

    public Boolean getVisibleForModerators() {
        return isVisibleForModerators;
    }

    public void setVisibleForModerators(Boolean visibleForFarmers) {
        isVisibleForModerators = visibleForFarmers;
    }

    public Boolean getVisibleOnNav() {
        return isVisibleOnNav;
    }

    public void setVisibleOnNav(Boolean visibleOnNav) {
        isVisibleOnNav = visibleOnNav;
    }
}
