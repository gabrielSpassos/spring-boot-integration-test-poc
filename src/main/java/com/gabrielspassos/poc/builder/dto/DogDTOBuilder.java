package com.gabrielspassos.poc.builder.dto;

import com.gabrielspassos.poc.dto.DogDTO;
import com.gabrielspassos.poc.entity.DogEntity;

public class DogDTOBuilder {

    public static DogDTO build(DogEntity entity) {
        return DogDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .species(entity.getSpecies())
                .build();
    }
}
