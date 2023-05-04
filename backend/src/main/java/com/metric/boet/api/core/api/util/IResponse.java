package com.metric.boet.api.core.api.util;

public interface IResponse<R> {
    boolean isSuccessful();

    R getData();
}
