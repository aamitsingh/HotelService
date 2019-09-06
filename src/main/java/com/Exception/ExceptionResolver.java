package com.Exception;

import org.apache.logging.log4j.core.config.Order;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

/**
 * Created by amitsingh on 27/06/19.
 */

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionResolver {

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse handleValidationException(HttpServletRequest request, ValidationException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST, ex.getStackTrace().toString(), request.getRequestURL().toString());
    }
}