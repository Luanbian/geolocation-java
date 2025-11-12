package dev.useCode.geolocation.presentation.controllers;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LocationControllerTest {

    @Test
    void saveLocationSuccess() {
        // given
        LocationController locationController = new LocationController();

        // when
        var response = locationController.saveLocation();

        // then
        assertEquals(201, response.getStatusCodeValue());
        assertTrue(response.getBody().data().isPresent());
        assertEquals("Location Data", response.getBody().data().get());
        assertFalse(response.getBody().errors().isPresent());
    }
}