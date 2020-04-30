package com.gabrielspassos.poc.controller.v1.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleErrorResponse {

    private String errorCode;
    private String errorMessage;

}
