package dev.useCode.geolocation.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LocationController.class)
class LocationControllerTest {
    private Faker faker;
    private LocationController locationController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setup() {
        faker = new Faker();
        locationController = new LocationController();
    }

    @Test
    void saveLocationSuccess() {
        // given
        CreateLocationDto locationDto = new CreateLocationDto(
                faker.name().fullName(),
                faker.address().fullAddress(),
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                "RESTAURANT"
        );

        // when
        var response = locationController.saveLocation(locationDto);

        // then
        assertEquals(201, response.getStatusCode().value());
        var body = response.getBody();
        assertNotNull(body, "Response body should not be null");
        assertEquals("controller.locationController", body.code());
        assertEquals("Location saved successfully", body.message());
        assertNotNull(body.transaction());
        assertTrue(body.data().isPresent());
        assertFalse(body.errors().isPresent());
    }

    @Test
    void saveLocationValidationError() throws Exception {
        // given
        CreateLocationDto locationDto = new CreateLocationDto(
                "",
                // Invalid name
                faker.address().fullAddress(),
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                "INVALID_TYPE"
                // Invalid type
        );

        String locationDtoJson = objectMapper.writeValueAsString(locationDto);

        // when & then
        mockMvc.perform(post("/api/locations").contentType(MediaType.APPLICATION_JSON).content(locationDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("validation.error"))
                .andExpect(jsonPath("$.message").value("Validation failed"));
    }
}