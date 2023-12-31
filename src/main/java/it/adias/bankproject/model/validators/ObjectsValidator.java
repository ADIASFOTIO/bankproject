package it.adias.bankproject.model.validators;

import it.adias.bankproject.model.exceptions.ObjectValidationException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;
@Component
public class ObjectsValidator<T> {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();
    public void validate(T objectTovalidate){
       Set<ConstraintViolation<T>> violations = validator.validate(objectTovalidate);
       if (!violations.isEmpty()){
           Set<String> errorMessages = violations.stream()
                   .map(v -> v.getMessage())
                   .collect(Collectors.toSet());
           throw new ObjectValidationException(errorMessages, objectTovalidate.getClass().getName());
       }
    }
}
