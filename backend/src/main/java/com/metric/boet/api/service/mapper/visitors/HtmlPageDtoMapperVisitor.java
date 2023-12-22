package com.metric.boet.api.service.mapper.visitors;

import com.metric.boet.api.service.mapper.EntityDtoVisitor;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.entity.HtmlPage;
import org.springframework.stereotype.Component;

@Component
public class HtmlPageDtoMapperVisitor implements EntityDtoVisitor<HtmlPage, HtmlPageDto> {

    @Override
    public HtmlPageDto visit(HtmlPage request) {
        HtmlPageDto mappedObject = new HtmlPageDto();

        mappedObject.setUrl(request.getUrl());
        mappedObject.setCreatedAt(request.getCreatedAt());
        mappedObject.setUpdatedAt(request.getUpdatedAt());
        mappedObject.setHtml(request.getHtml());
        mappedObject.setVisibleForModerators(request.getVisibleForModerators());
        mappedObject.setVisibleForUsers(request.getVisibleForUsers());
        mappedObject.setVisibleOnNav(request.getVisibleOnNav());

        return mappedObject;
    }
}
