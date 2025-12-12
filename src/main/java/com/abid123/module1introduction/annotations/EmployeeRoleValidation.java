package com.abid123.module1introduction.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER})

@Constraint(validatedBy = {EmployeeRoleValidator.class})
public @interface EmployeeRoleValidation {
    String message() default "Please choose the role either admin or user";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
