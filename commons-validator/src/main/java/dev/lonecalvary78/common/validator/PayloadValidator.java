package dev.lonecalvary78.common.validator;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import dev.lonecalvary78.common.validator.group.OperationType;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

public class PayloadValidator<T> {
    private static PayloadValidator INSTANCE = new PayloadValidator<>();
    public static <T> PayloadValidator<T> getInstance() {
        return INSTANCE;
    }

    public Map<String,String> validate(T payload) {
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var constraintViolations = validator.validate(payload);
        return mapToErrorMap(constraintViolations);
    }

    public Map<String,String> validateWithGroup(T payload, OperationType operationType) {
        var validator = Validation.buildDefaultValidatorFactory().getValidator();
        var constraintViolations = validator.validate(payload, operationType.getClass());
        return mapToErrorMap(constraintViolations);        
    }

    private Map<String, String> mapToErrorMap(Set<ConstraintViolation<T>> constraintViolations) {
        Map<String, String> errors = new HashMap<>();
        if(constraintViolations == null)
           throw new IllegalArgumentException("constraintViolations is required.");

        if(constraintViolations != null && !constraintViolations.isEmpty()) {
            for (ConstraintViolation<T> constraintViolation: constraintViolations) {
                errors.put(constraintViolation.getPropertyPath().toString(), constraintViolation.getMessage());
            }
        }
        return errors;        
    }
}
