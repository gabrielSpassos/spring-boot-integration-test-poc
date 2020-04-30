package com.gabrielspassos.poc.controller.v1;

import com.gabrielspassos.poc.entity.DogEntity;
import com.gabrielspassos.poc.repository.DogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DogRepository dogRepository;

    @Test
    public void shouldCreateDog() throws Exception {
        String name = "Mel";
        String species = "Vira-Lata";

        mockMvc.perform(post("/v1/dogs")
                .contentType("application/json")
                .content(Resources.DOG_REQUEST))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(3))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("species").value(species));

        DogEntity dogEntity = dogRepository.findById(3L).get();
        assertEquals(3L, dogEntity.getId());
        assertEquals(name, dogEntity.getName());
        assertEquals(species, dogEntity.getSpecies());
    }

    @Test
    public void shouldReturnDogById() throws Exception {
        String name = "Toby";
        String species = "Pug";

        mockMvc.perform(get("/v1/dogs/{id}", 2)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(2))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("species").value(species));

        DogEntity dogEntity = dogRepository.findById(2L).get();
        assertEquals(2L, dogEntity.getId());
        assertEquals(name, dogEntity.getName());
        assertEquals(species, dogEntity.getSpecies());
    }

    @Test
    public void shouldUpdateDog() throws Exception {
        String name = "Mel";
        String species = "Vira-Lata";

        mockMvc.perform(put("/v1/dogs/{id}", 1)
                .contentType("application/json")
                .content(Resources.DOG_REQUEST))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(1))
                .andExpect(jsonPath("name").value(name))
                .andExpect(jsonPath("species").value(species));

        DogEntity dogEntity = dogRepository.findById(1L).get();
        assertEquals(1L, dogEntity.getId());
        assertEquals(name, dogEntity.getName());
        assertEquals(species, dogEntity.getSpecies());
    }

    @Test
    public void shouldReturnErrorToGetDog() throws Exception {
        mockMvc.perform(get("/v1/dogs/{id}", 9999)
                .contentType("application/json"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("errorCode").value(1))
                .andExpect(jsonPath("errorMessage").value("Not found dog"));

        assertTrue(dogRepository.findById(9999L).isEmpty());
    }

    @Test
    public void shouldReturnErrorToUpdateDog() throws Exception {
        mockMvc.perform(put("/v1/dogs/{id}", 9998)
                .contentType("application/json")
                .content(Resources.DOG_REQUEST))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("errorCode").value(1))
                .andExpect(jsonPath("errorMessage").value("Not found dog"));

        assertTrue(dogRepository.findById(9998L).isEmpty());
    }

    private class Resources {
        static final String DOG_REQUEST =
                "{\n" +
                "  \"name\": \"Mel\",\n" +
                "  \"species\": \"Vira-Lata\"\n" +
                "}";
    }

}