package com.metric.boet.api.service.mapper;

import com.metric.boet.api.dto.BasicObjectDto;

public interface EntityDtoVisitor<Entity, Dto extends BasicObjectDto> {
    Dto visit(Entity entity);
}
