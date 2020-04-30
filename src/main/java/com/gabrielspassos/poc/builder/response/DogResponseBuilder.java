package com.gabrielspassos.poc.builder.response;

import com.gabrielspassos.poc.controller.v1.response.DogResponse;
import com.gabrielspassos.poc.dto.DogDTO;

public class DogResponseBuilder {

    public static DogResponse build(DogDTO dogDTO) {
        return DogResponse.builder()
                .id(dogDTO.getId())
                .name(dogDTO.getName())
                .species(dogDTO.getSpecies())
                .build();
    }
}
