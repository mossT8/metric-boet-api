package com.metric.boet.api.core.mapper.imp;

import com.metric.boet.api.core.mapper.IDtoMapper;
import com.metric.boet.api.dto.HtmlPageDto;
import com.metric.boet.api.entity.HtmlPage;
import jdk.jshell.spi.ExecutionControl;
import org.springframework.stereotype.Component;

@Component
public class HtmlPageMapper implements IDtoMapper<HtmlPage, HtmlPageDto> {

    @Override
    public HtmlPageDto removeSensitiveInformation(HtmlPage inputObject) throws ExecutionControl.NotImplementedException {
        HtmlPageDto mappedObject = new HtmlPageDto();

        mappedObject.setUrl(inputObject.getUrl());
        mappedObject.setCreatedAt(inputObject.getCreatedAt());
        mappedObject.setUpdatedAt(inputObject.getUpdatedAt());
        mappedObject.setHtml(inputObject.getHtml());

        return mappedObject;
    }
}
