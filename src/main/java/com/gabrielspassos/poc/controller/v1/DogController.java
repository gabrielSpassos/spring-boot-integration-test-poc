package com.gabrielspassos.poc.controller.v1;

import com.gabrielspassos.poc.builder.response.DogResponseBuilder;
import com.gabrielspassos.poc.controller.v1.request.DogRequest;
import com.gabrielspassos.poc.controller.v1.response.DogResponse;
import com.gabrielspassos.poc.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RestController
@RequestMapping(value = "/dogs")
public class DogController implements BaseVersion {

    @Autowired
    private DogService dogService;

    @PostMapping
    public ResponseEntity<DogResponse> createDog(@RequestBody DogRequest dogRequest) {
        return Stream.of(dogService.createDog(dogRequest))
                .map(DogResponseBuilder::build)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DogResponse> getDogById(@PathVariable Long id) {
        return Stream.of(dogService.getDogById(id))
                .map(DogResponseBuilder::build)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<DogResponse> updateDogById(@PathVariable Long id, @RequestBody DogRequest dogRequest) {
        return Stream.of(dogService.updateDogById(id, dogRequest))
                .map(DogResponseBuilder::build)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }
}
