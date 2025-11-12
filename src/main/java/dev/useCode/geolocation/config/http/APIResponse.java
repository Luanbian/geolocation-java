package dev.useCode.geolocation.config.http;

import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.UUID;

public record APIResponse<T, E>(String code, String message, UUID transaction, Optional<T> data, Optional<E> errors) {
    public static <T, E> APIResponse<T, E> of(String code, String message, UUID transaction, T data, E errors) {
        return new APIResponse<>(code, message, transaction, Optional.ofNullable(data), Optional.ofNullable(errors));
    }

    public static <T> ResponseEntity<APIResponse<T, String>> httpResponse(int statusCode, String code, String message, UUID transaction, T data) {
        APIResponse<T, String> apiResponse = APIResponse.of(code, message, transaction, data, null);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }

    public static <T, E> ResponseEntity<APIResponse<T, E>> httpResponse(int statusCode, String code, String message, UUID transaction, T data, E errors) {
        APIResponse<T, E> apiResponse = APIResponse.of(code, message, transaction, data, errors);
        return ResponseEntity.status(statusCode).body(apiResponse);
    }
}
