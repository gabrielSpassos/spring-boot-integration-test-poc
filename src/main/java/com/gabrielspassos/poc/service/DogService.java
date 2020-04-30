package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.builder.dto.DogDTOBuilder;
import com.gabrielspassos.poc.builder.entity.DogEntityBuilder;
import com.gabrielspassos.poc.controller.v1.request.DogRequest;
import com.gabrielspassos.poc.dto.DogDTO;
import com.gabrielspassos.poc.entity.DogEntity;
import com.gabrielspassos.poc.exception.NotFoundException;
import com.gabrielspassos.poc.repository.DogRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogService {

    @Autowired
    private DogRepository dogRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final String NOT_FOUND_CODE = "1";
    private final String NOT_FOUND_MESSAGE = "Not found dog";

    public DogDTO createDog(DogRequest dogRequest) {
        DogEntity dogEntity = DogEntityBuilder.build(dogRequest);
        DogEntity savedDog = saveDog(dogEntity);
        return DogDTOBuilder.build(savedDog);
    }

    public DogDTO getDogById(Long id) {
        logger.info("Searching for dog by id {}", id);
        DogDTO dogDTO = dogRepository.findById(id)
                .map(DogDTOBuilder::build)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND_CODE, NOT_FOUND_MESSAGE));
        logger.info("Found dog {}", dogDTO);
        return dogDTO;
    }

    public DogDTO updateDogById(Long id, DogRequest dogRequest) {
        DogDTO dogDTO = getDogById(id);
        DogEntity dogEntity = DogEntityBuilder.build(dogDTO, dogRequest);
        DogEntity updatedDog = saveDog(dogEntity);
        return DogDTOBuilder.build(updatedDog);
    }

    private DogEntity saveDog(DogEntity dogEntity) {
        logger.info("Saving dog {}", dogEntity);
        DogEntity savedDog = dogRepository.save(dogEntity);
        logger.info("Saved dog {}", savedDog);
        return savedDog;
    }
}
