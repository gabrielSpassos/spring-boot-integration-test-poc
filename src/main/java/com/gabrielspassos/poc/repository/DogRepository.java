package com.gabrielspassos.poc.repository;

import com.gabrielspassos.poc.entity.DogEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<DogEntity, Long> {
}
