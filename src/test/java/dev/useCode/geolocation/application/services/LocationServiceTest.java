package dev.useCode.geolocation.application.services;

import dev.useCode.geolocation.application.repository.LocationRepository;
import dev.useCode.geolocation.domain.entities.Location;
import dev.useCode.geolocation.mocks.LocationMockBuilder;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LocationServiceTest {
    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private LocationService locationService;

    @Test
    void saveLocation() {
        // given
        CreateLocationDto locationDto = LocationMockBuilder.validLocationDto();
        Location locationMock = LocationMockBuilder.validLocationEntity();

        // when
        Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(locationMock);
        Location response = locationService.saveLocation(locationDto);

        // then
        Mockito.verify(locationRepository, Mockito.times(1)).save(Mockito.any(Location.class));
        assertEquals(locationMock, response);
    }
}