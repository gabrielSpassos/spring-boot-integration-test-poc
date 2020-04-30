package com.gabrielspassos.poc.error;

import com.gabrielspassos.poc.builder.response.SimpleErrorResponseBuilder;
import com.gabrielspassos.poc.controller.v1.response.SimpleErrorResponse;
import com.gabrielspassos.poc.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalErrorHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String DEFAULT_ERROR_CODE = "999";
    private final String DEFAULT_ERROR_MESSAGE = "Unavailable system";

    @ExceptionHandler
    public ResponseEntity<SimpleErrorResponse> handleException(Throwable throwable) {
        if(throwable instanceof BusinessException) {
            BusinessException businessException = (BusinessException) throwable;
            logger.error("Expected error", businessException);
            SimpleErrorResponse simpleErrorResponse = SimpleErrorResponseBuilder
                    .build(businessException.getSimpleError());
            return buildResponse(businessException.getHttpStatus(), simpleErrorResponse);
        }
        logger.error("Unexpected error", throwable);
        SimpleErrorResponse simpleErrorResponse = SimpleErrorResponseBuilder
                .build(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
        return buildResponse(HttpStatus.INTERNAL_SERVER_ERROR, simpleErrorResponse);
    }

    private ResponseEntity<SimpleErrorResponse> buildResponse(HttpStatus status, SimpleErrorResponse body) {
        return ResponseEntity.status(status).body(body);
    }
}
