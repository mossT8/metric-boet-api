package com.metric.boet.api.util.annotations.validator;

import com.metric.boet.api.util.annotations.NoIdField;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class NoIdFieldValidator implements ConstraintValidator<NoIdField, Object> {

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Check if the DTO class has an "id" field
        Field[] fields = value.getClass().getDeclaredFields();
        for (Field field : fields) {
            if ("id".equals(field.getName())) {
                return false;
            }
        }
        return true;
    }
}

