package dev.useCode.geolocation.mocks;

import dev.useCode.geolocation.domain.entities.Location;
import dev.useCode.geolocation.presentation.dtos.CreateLocationDto;
import net.datafaker.Faker;

public class LocationMockBuilder {
    private static final Faker faker = new Faker();

    public static CreateLocationDto validLocationDto() {
        return new CreateLocationDto(
                faker.name().fullName(),
                faker.address().fullAddress(),
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                "RESTAURANT"
        );
    }

    public static CreateLocationDto invalidLocationDto() {
        return new CreateLocationDto(
                "", // Invalid name
                faker.address().fullAddress(),
                "invalid_longitude", // Invalid longitude
                faker.number().numberBetween(-90, 90) + "." + faker.number().numberBetween(0, 99999),
                "UNKNOWN_TYPE" // Invalid type
        );
    }

    public static Location validLocationEntity() {
        CreateLocationDto dto = validLocationDto();
        return Location.builder()
                .name(dto.name())
                .address(dto.address())
                .longitude(dto.longitude())
                .latitude(dto.latitude())
                .type(dev.useCode.geolocation.presentation.CompanyTypes.valueOf(dto.type()))
                .build();
    }

}
