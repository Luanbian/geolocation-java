package dev.useCode.geolocation.presentation.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CreateLocationDto(
        @NotEmpty(message = "Name cannot be empty")
        @NotNull(message = "Name cannot be null")
        String name,

        @NotEmpty(message = "Address cannot be empty")
        @NotNull(message = "Address cannot be null")
        String address,

        @NotEmpty(message = "Longitude cannot be empty")
        @NotNull(message = "Longitude cannot be null")
        String longitude,

        @NotEmpty(message = "Latitude cannot be empty")
        @NotNull(message = "Latitude cannot be null")
        String latitude,

        @NotEmpty(message = "Type cannot be empty")
        @NotNull(message = "Type cannot be null")
        @Pattern(regexp = "RESTAURANT|HEALTHCARE|EDUCATION|ENTERTAINMENT|TRANSPORTATION|HOSPITALITY|OTHER", message = "Type must be one of the predefined company types")
        String type
) {
}