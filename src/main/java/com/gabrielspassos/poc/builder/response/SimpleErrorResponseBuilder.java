package com.gabrielspassos.poc.builder.response;

import com.gabrielspassos.poc.controller.v1.response.SimpleErrorResponse;
import com.gabrielspassos.poc.dto.SimpleErrorDTO;

public class SimpleErrorResponseBuilder {

    public static SimpleErrorResponse build(SimpleErrorDTO simpleErrorDTO) {
        return build(simpleErrorDTO.getCode(), simpleErrorDTO.getMessage());
    }

    public static SimpleErrorResponse build(String code, String message) {
        return new SimpleErrorResponse(code, message);
    }
}
