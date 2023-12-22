package com.metric.boet.api.util.annotations;

import com.metric.boet.api.util.annotations.validator.NoIdFieldValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoIdFieldValidator.class)
public @interface NoIdField {

    String message() default "DTO classes should not contain an 'id' field";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

