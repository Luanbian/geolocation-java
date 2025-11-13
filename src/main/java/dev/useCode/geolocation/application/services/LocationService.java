package dev.useCode.geolocation.application.services;

import dev.useCode.geolocation.application.repository.LocationRepository;
import dev.useCode.geolocation.domain.entities.Location;
import dev.useCode.geolocation.presentation.CompanyTypes;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location saveLocation(CreateLocationDto locationDto) {
        Location location = Location.builder()
                .name(locationDto.name())
                .address(locationDto.address())
                .longitude(locationDto.longitude())
                .latitude(locationDto.latitude())
                .type(CompanyTypes.valueOf(locationDto.type()))
                .build();

        return locationRepository.save(location);
    }
}
