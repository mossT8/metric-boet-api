package com.metric.boet.api.util.api.closed;

import com.metric.boet.api.util.annotations.PrivateWebAppEndpoint;
import com.metric.boet.api.util.api.AbstractWebAppService;
import com.metric.boet.api.util.api.request.AbstractWebAppEndpointPayload;
import com.metric.boet.api.util.api.request.WebAppApiRequestHolderBean;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

import static com.metric.boet.api.util.api.AbstractWebAppEndpointHandler.toJsonObject;

@Service
public class PrivateApiGatewayService extends AbstractWebAppService<PrivateWebAppEndpoint, AbstractPrivateWebAppEndpointHandler<AbstractWebAppEndpointPayload>> {
    @Autowired
    public PrivateApiGatewayService(ListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public ResponseEntity<?> handleRequestProcess(HttpServletRequest request, WebAppApiRequestHolderBean payload) {

        for (AbstractPrivateWebAppEndpointHandler<AbstractWebAppEndpointPayload> privateWebAppEndpointHandler : endpointHandlers.values()) {
            if (privateWebAppEndpointHandler.getClass().getName().equals(payload.getFullyQualifiedClassName())) {
                try {
                    AbstractWebAppEndpointPayload mappedPayload = privateWebAppEndpointHandler.getRequestFromString(toJsonObject(payload.getPayload().toString()));
                    return ResponseEntity.ok().body(privateWebAppEndpointHandler.processRequest(request, mappedPayload));
                } catch (Exception e) {
                    logger.error("cant perform request: ", e);
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error: Request details provided has caused an internal Server Error.");
                }
            }
        }

        return ResponseEntity.notFound().build();
    }
}
