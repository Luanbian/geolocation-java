package dev.useCode.geolocation.config.http;

import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public record APIResponse<T>(String code, String message, UUID transaction, Optional<T> data, Optional<String> errors) {
    public static <T> APIResponse<T> of(String code, String message, UUID transaction, T data, String errors) {
        return new APIResponse<>(code, message, transaction, Optional.ofNullable(data), Optional.ofNullable(errors));
    }

    public static <T> ResponseEntity<APIResponse<T>> httpResponse(int statusCode, String code, String message, UUID transaction, T data, String errors) {
        APIResponse<T> apiResponse = APIResponse.of(code, message, transaction, data, errors);
       return ResponseEntity.status(statusCode).body(apiResponse);
    }

}
