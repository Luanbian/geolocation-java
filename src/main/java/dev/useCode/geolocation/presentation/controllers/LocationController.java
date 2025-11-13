package dev.useCode.geolocation.presentation.controllers;

import dev.useCode.geolocation.application.services.LocationService;
import dev.useCode.geolocation.config.http.APIResponse;
import dev.useCode.geolocation.domain.entities.Location;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<APIResponse<Location, String>> saveLocation(@Valid @RequestBody CreateLocationDto locationDto) {
        try {
            Location savedLocation = locationService.saveLocation(locationDto);

            return APIResponse.httpResponse(
                    201,
                    "controller.locationController",
                    "Location saved successfully",
                    UUID.randomUUID(),
                    savedLocation,
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
