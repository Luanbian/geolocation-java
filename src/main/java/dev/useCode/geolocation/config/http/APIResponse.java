package dev.useCode.geolocation.config.http;

import java.util.Optional;
import java.util.UUID;

public record APIResponse<T,E>(String code, String message, UUID transaction, Optional<T> data, Optional<E> errors) {
    public static <T,E> APIResponse<T,E> of(String code, String message, UUID transaction, T data, E errors) {
        return new APIResponse<>(code, message, transaction, Optional.ofNullable(data), Optional.ofNullable(errors));
    }
}
