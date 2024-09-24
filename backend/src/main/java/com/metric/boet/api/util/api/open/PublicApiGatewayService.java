package com.metric.boet.api.util.api.open;

import com.metric.boet.api.util.annotations.PublicWebAppEndpoint;
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
public class PublicApiGatewayService extends AbstractWebAppService<PublicWebAppEndpoint, AbstractPublicWebAppEndpointHandler<AbstractWebAppEndpointPayload>> {

    @Autowired
    public PublicApiGatewayService(ListableBeanFactory beanFactory) {
        super(beanFactory);
    }

    @Override
    public ResponseEntity<?> handleRequestProcess(HttpServletRequest request, WebAppApiRequestHolderBean payload) {
        for (AbstractPublicWebAppEndpointHandler<AbstractWebAppEndpointPayload> publicWebAppEndpointHandler : endpointHandlers.values()) {
            if (publicWebAppEndpointHandler.getClass().getName().equals(payload.getFullyQualifiedClassName())) {
                try {
                    AbstractWebAppEndpointPayload mappedPayload = publicWebAppEndpointHandler.getRequestFromString(toJsonObject(payload.getPayload().toString()));
                    if (publicWebAppEndpointHandler.authoriseRequest(request, mappedPayload))
                        return ResponseEntity.ok().body(publicWebAppEndpointHandler.processRequest(request, mappedPayload));
                    else
                        return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body("Please ensure you have necessary credentials to access this endpoint with given request.");
                } catch (Exception e) {
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(e.getMessage());
                }
            }
        }

        return ResponseEntity.notFound().build();
    }
}
