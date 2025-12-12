package com.abid123.module1introduction.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.ArrayList;
import java.util.List;

public class EmployeeRoleValidator implements ConstraintValidator<EmployeeRoleValidation,String> {

    @Override
    public boolean isValid(String inputRole, ConstraintValidatorContext constraintValidatorContext) {
        List<String> roles = new ArrayList<>();
        roles.add("ADMIN");
        roles.add("USER");
        return roles.contains(inputRole);
    }
}
