package com.gabrielspassos.poc.builder.entity;

import com.gabrielspassos.poc.controller.v1.request.DogRequest;
import com.gabrielspassos.poc.dto.DogDTO;
import com.gabrielspassos.poc.entity.DogEntity;

public class DogEntityBuilder {

    public static DogEntity build(DogRequest dogRequest) {
        return DogEntity.builder()
                .name(dogRequest.getName())
                .species(dogRequest.getSpecies())
                .build();
    }

    public static DogEntity build(DogDTO dogDTO, DogRequest dogRequest) {
        return DogEntity.builder()
                .id(dogDTO.getId())
                .name(dogRequest.getName())
                .species(dogRequest.getSpecies())
                .build();
    }
}
