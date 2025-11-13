package dev.useCode.geolocation.presentation.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.useCode.geolocation.application.services.LocationService;
import dev.useCode.geolocation.domain.entities.Location;
import dev.useCode.geolocation.mocks.LocationMockBuilder;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(LocationController.class)
class LocationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private LocationService locationService;

    @Test
    void saveLocationSuccess() throws Exception {
        // given
        CreateLocationDto locationDto = LocationMockBuilder.validLocationDto();
        Location mockLocation = LocationMockBuilder.validLocationEntity();

        // when
        Mockito.when(locationService.saveLocation(locationDto)).thenReturn(mockLocation);

        // then
        mockMvc.perform(post("/api/locations").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.code").value("controller.locationController"))
                .andExpect(jsonPath("$.message").value("Location saved successfully"))
                .andExpect(jsonPath("$.data").exists());
    }

    @Test
    void saveLocationFailed() throws Exception {
        // given
        CreateLocationDto locationDto = LocationMockBuilder.validLocationDto();

        // when
        Mockito.when(locationService.saveLocation(locationDto))
                .thenThrow(new RuntimeException("Database error"));

        // then
        mockMvc.perform(post("/api/locations").contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(locationDto)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.code").value("controller.locationController.error"))
                .andExpect(jsonPath("$.message").value("An error occurred while saving location"))
                .andExpect(jsonPath("$.errors").value("Database error"));
    }

    @Test
    void saveLocationValidationError() throws Exception {
        // given
        CreateLocationDto locationDto = LocationMockBuilder.invalidLocationDto();
        String locationDtoJson = objectMapper.writeValueAsString(locationDto);


        // when & then
        mockMvc.perform(post("/api/locations").contentType(MediaType.APPLICATION_JSON).content(locationDtoJson))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("validation.error"))
                .andExpect(jsonPath("$.message").value("Validation failed"));
    }
}