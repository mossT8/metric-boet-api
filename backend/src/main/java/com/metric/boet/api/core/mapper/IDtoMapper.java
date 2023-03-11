package com.metric.boet.api.core.mapper;

import jdk.jshell.spi.ExecutionControl;

public interface IDtoMapper<I, O> {
    default O removeSensitiveInformation(I inputObject) throws ExecutionControl.NotImplementedException {
        throw new ExecutionControl.NotImplementedException("Method removeSensitiveInformation() not implemented as per interface IDtoMapper requirements.");
    }
}
