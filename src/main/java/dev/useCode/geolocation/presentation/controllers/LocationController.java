package dev.useCode.geolocation.presentation.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    @PostMapping
    public ResponseEntity<String> saveLocation() {
        return ResponseEntity.ok("Location saved successfully");
    }
}
