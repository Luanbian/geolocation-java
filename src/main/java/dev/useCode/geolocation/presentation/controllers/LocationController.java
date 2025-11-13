package dev.useCode.geolocation.presentation.controllers;

import dev.useCode.geolocation.application.services.LocationService;
import dev.useCode.geolocation.config.http.APIResponse;
import dev.useCode.geolocation.domain.entities.Location;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping
    public ResponseEntity<APIResponse<List<Location>, String>> listLocations() {
        try {
            List<Location> locations = locationService.listLocations();

            return APIResponse.httpResponse(
                    200,
                    "controller.locationController",
                    "Locations retrieved successfully",
                    UUID.randomUUID(),
                    locations,
                    null
            );
        } catch (Exception exception) {
            return APIResponse.httpResponse(
                    500,
                    "controller.locationController.error",
                    "An error occurred while retrieving locations",
                    UUID.randomUUID(),
                    null,
                    exception.getMessage()
            );
        }
    }
}
