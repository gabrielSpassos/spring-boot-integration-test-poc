package com.gabrielspassos.poc.exception;

import com.gabrielspassos.poc.dto.SimpleErrorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public abstract class BusinessException extends RuntimeException {

    private SimpleErrorDTO simpleError;
    private HttpStatus httpStatus;

    public BusinessException(String code, String message, HttpStatus httpStatus) {
        this.simpleError = buildSimpleError(code, message);
        this.httpStatus = httpStatus;
    }

    private SimpleErrorDTO buildSimpleError(String code, String message) {
        return new SimpleErrorDTO(code, message);
    }
}
