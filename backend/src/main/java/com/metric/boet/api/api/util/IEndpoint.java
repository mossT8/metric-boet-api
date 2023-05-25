package com.metric.boet.api.api.util;

import java.net.http.HttpRequest;

public interface IEndpoint<P extends IPayload, R> {
    boolean validateEndpoint(HttpRequest serverRequest, P payload);

    R performAction(HttpRequest serverRequest, P payload);
}
