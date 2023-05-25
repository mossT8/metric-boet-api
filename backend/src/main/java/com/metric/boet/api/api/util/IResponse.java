package com.metric.boet.api.api.util;

public interface IResponse<R> {
    boolean isSuccessful();

    R getData();
}
