package com.metric.boet.api.core.mapper;

import com.metric.boet.api.dto.BasicObjectDto;
import jdk.jshell.spi.ExecutionControl;

public interface IDtoMapper<I, O extends BasicObjectDto> {
    O removeSensitiveInformation(I inputObject) throws ExecutionControl.NotImplementedException;
}
