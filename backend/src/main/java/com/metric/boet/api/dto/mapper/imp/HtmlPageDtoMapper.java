package com.metric.boet.api.dto.mapper.imp;

import com.metric.boet.api.dto.mapper.IDtoMapper;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.entity.HtmlPage;
import org.springframework.stereotype.Component;

@Component
public class HtmlPageDtoMapper implements IDtoMapper<HtmlPage, HtmlPageDto> {

    @Override
    public HtmlPageDto removeSensitiveInformation(HtmlPage inputObject) {
        HtmlPageDto mappedObject = new HtmlPageDto();

        mappedObject.setUrl(inputObject.getUrl());
        mappedObject.setCreatedAt(inputObject.getCreatedAt());
        mappedObject.setUpdatedAt(inputObject.getUpdatedAt());
        mappedObject.setHtml(inputObject.getHtml());
        mappedObject.setVisibleForModerators(inputObject.getVisibleForModerators());
        mappedObject.setVisibleForUsers(inputObject.getVisibleForUsers());
        mappedObject.setVisibleOnNav(inputObject.getVisibleOnNav());

        return mappedObject;
    }
}
