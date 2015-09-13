package com.affaince.subscription.subscriptionableitem.registration.web.configuration;

import com.affaince.subscription.subscriptionableitem.registration.web.exception.SubscriptionableItemNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.ui.ModelMap;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 13-09-2015.
 */
@ControllerAdvice(annotations = RestController.class)
public class RestControllerExceptionHandler {

    @ResponseBody
    @ExceptionHandler({SubscriptionableItemNotFoundException.class, HttpMessageNotReadableException.class})
    protected Object handleResourceNotFoundException(Exception exception) {
        System.out.println("in handleResourceNotFoundException @@@@@@@");
        HttpStatus status = HttpStatus.NOT_FOUND;
        Map body = buildErrorAttributes(exception.getMessage());
        return new ResponseEntity<Map<String, Object>>(body, status);
    }

    @ResponseBody
    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected Object handleInvalidRequestArgumentHandler(MethodArgumentNotValidException exception) {
        System.out.println("in handleInvalidRequestArgumentHandler @@@@@@@");
        HttpStatus status = HttpStatus.BAD_REQUEST;
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        ModelMap map = new ModelMap();
        List<ModelMap> mapList = new ArrayList<>();
        for (FieldError fieldError : fieldErrors) {
            ModelMap errorMap = new ModelMap();
            errorMap.addAttribute("property", fieldError.getField());
            errorMap.addAttribute("message", fieldError.getDefaultMessage());
            mapList.add(errorMap);
        }
        map.addAttribute("errors", mapList);
        return new ResponseEntity<Map<String, Object>>(map, status);
    }

    private Map<String, Object> buildErrorAttributes(String message) {
        Map<String, Object> errorAttributes = new LinkedHashMap<>();
        errorAttributes.put("error message", message);
        return errorAttributes;
    }
}
