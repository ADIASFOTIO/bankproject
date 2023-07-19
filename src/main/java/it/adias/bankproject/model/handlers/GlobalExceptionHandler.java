package it.adias.bankproject.model.handlers;

import it.adias.bankproject.model.exceptions.ObjectValidationException;
import it.adias.bankproject.model.exceptions.OperationNonPermittedException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    // Ritornare un ogetto json con gli errori
    @ExceptionHandler(ObjectValidationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(ObjectValidationException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Object not valid exception")
                .errorSource(exception.getViolationSource())
                .validationErrors(exception.getViolations())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(EntityNotFoundException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(representation);
    }
    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(OperationNonPermittedException exception){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handleException(){
        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("A user already exits with the provided email")
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }
}
