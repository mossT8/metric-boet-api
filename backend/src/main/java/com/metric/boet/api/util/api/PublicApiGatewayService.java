package com.metric.boet.api.util.api;

import com.metric.boet.api.util.annotations.PublicWebAppEndpoint;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class PublicApiGatewayService {

    private final ListableBeanFactory beanFactory;
    private final Map<String, Object> endpointProcessors;

    @Autowired
    public PublicApiGatewayService(ListableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.endpointProcessors = beanFactory.getBeansWithAnnotation(PublicWebAppEndpoint.class);
    }

    public ResponseEntity<?> handleRequestProcess(HttpServletRequest request, ApiRequest<?> payload) {
        for (Object endpointProcessor : endpointProcessors.values()) {
            if (endpointProcessor instanceof AbstractPublicWebAppEndpointHandler
                    && endpointProcessor.getClass().getName().equals(payload.getFullyQualifiedClassName())) {
                @SuppressWarnings("unchecked")
                AbstractPublicWebAppEndpointHandler<AbstractWebAppEndpointApiRequest> processor = (AbstractPublicWebAppEndpointHandler<AbstractWebAppEndpointApiRequest>) endpointProcessor;
                try {
                    AbstractWebAppEndpointApiRequest mappedPayload = processor.getRequestFromString(toJsonObject(payload.getRequestObject().toString()));
                    if (processor.authoriseRequest(request, mappedPayload))
                        return ResponseEntity.ok().body(processor.processRequest(request, mappedPayload));
                    else
                        return ResponseEntity
                                .status(HttpStatus.FORBIDDEN)
                                .body("Please ensure you have necessary credentials to access this endpoint with given request.");
                } catch (Exception e) {
                    return ResponseEntity
                            .status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body("Error: Request details provided has caused an internal Server Error.");
                }
            }
        }

        return ResponseEntity.notFound().build();
    }

    private String toJsonObject(String linkedHashMapString) {
        String jsonString = linkedHashMapString
                .replaceAll("^\\{", "{\"") // Remove the leading curly brace
                .replaceAll("\\}$", "\"}") // Remove the trailing curly brace
                .replaceAll(", ", "\", \"") // Replace the commas and spaces with double quotes and comma
                .replaceAll("=", "\":\""); // Replace the equal sign with double quote and equal sign
        return jsonString;
    }
}
