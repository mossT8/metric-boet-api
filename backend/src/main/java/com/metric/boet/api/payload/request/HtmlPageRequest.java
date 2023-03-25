package com.metric.boet.api.payload.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class HtmlPageRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String url;

    @NotBlank
    private String html;

    @NotNull
    private Boolean visibleForModerators;

    @NotNull
    private Boolean visibleForUsers;

    @NotNull
    private Boolean visibleOnNav;

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

    public Boolean getVisibleForModerators() {
        return visibleForModerators;
    }

    public void setVisibleForModerators(Boolean visibleForModerators) {
        this.visibleForModerators = visibleForModerators;
    }

    public Boolean getVisibleForUsers() {
        return visibleForUsers;
    }

    public void setVisibleForUsers(Boolean visibleForUsers) {
        this.visibleForUsers = visibleForUsers;
    }

    public Boolean getVisibleOnNav() {
        return visibleOnNav;
    }

    public void setVisibleOnNav(Boolean visibleOnNav) {
        this.visibleOnNav = visibleOnNav;
    }
}
