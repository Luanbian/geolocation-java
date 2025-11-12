package dev.useCode.geolocation.presentation.controllers;

import dev.useCode.geolocation.config.http.APIResponse;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @PostMapping
    public ResponseEntity<APIResponse<String, String>> saveLocation(@Valid @RequestBody CreateLocationDto locationDto) {
        try {
            return APIResponse.httpResponse(
                    201,
                    "controller.locationController",
                    "Location saved successfully",
                    UUID.randomUUID(),
                    "Location Data",
                    null
            );
        } catch (Exception exception) {
            return APIResponse.httpResponse(
                    500,
                    "controller.locationController.error",
                    "An error occurred while saving location",
                    UUID.randomUUID(),
                    null,
                    exception.getMessage()
            );
        }
    }
}
