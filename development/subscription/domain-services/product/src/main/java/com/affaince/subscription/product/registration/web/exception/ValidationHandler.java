package com.affaince.subscription.product.registration.web.exception;

import org.axonframework.commandhandling.interceptors.JSR303ViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 * Created by rbsavaliya on 12-09-2015.
 */
@ControllerAdvice
public class ValidationHandler {

    @ExceptionHandler
    public ResponseEntity validate(JSR303ViolationException e) {
        System.out.println("inside Validator Handler $$$$$$$$$$$$$$$$$$$$$");
        Set<ConstraintViolation<Object>> violations = e.getViolations();
        return new ResponseEntity(violations, HttpStatus.BAD_REQUEST);
    }
}
