package com.gabrielspassos.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SimpleErrorDTO {
    
    private String code;
    private String message;

}
