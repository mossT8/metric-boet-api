package com.metric.boet.api.payloads.request.basic;

import com.metric.boet.api.util.api.AbstractWebAppEndpointApiRequest;
import com.metric.boet.api.util.api.IPrettyObjects;

public class EmptyApiRequestAbstract extends AbstractWebAppEndpointApiRequest<KeyApiRequestAbstract> implements IPrettyObjects {
// empty as request context as jwt token gives user context
}
