package com.metric.boet.api.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "html_pages",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "url")
        })
public class HtmlPage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String url;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String html;

    @Column(nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private Date createdAt;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private Date updatedAt;

    @NotBlank
    private Boolean visibleOnNav;

    @NotBlank
    private Boolean visibleForUsers;

    @NotBlank
    private Boolean visibleForModerators;

    public HtmlPage() {
        this.visibleForUsers = true;
        this.visibleForModerators = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
