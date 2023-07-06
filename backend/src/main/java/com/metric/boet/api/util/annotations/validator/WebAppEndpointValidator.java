package com.metric.boet.api.util.annotations.validator;

import com.metric.boet.api.util.annotations.PublicWebAppEndpoint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Method;

public class WebAppEndpointValidator  implements ConstraintValidator<PublicWebAppEndpoint, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Check if the DTO class has an "id" field
        Method[] methods = value.getClass().getMethods();
        for (Method field : methods) {
            if ("id".equals(field.getName())) {
                return false;
            }
        }
        return true;
    }
}


