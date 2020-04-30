package com.gabrielspassos.poc.controller.v1.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DogResponse {

    private Long id;
    private String name;
    private String species;

}
