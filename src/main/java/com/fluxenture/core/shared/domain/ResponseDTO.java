package com.fluxenture.core.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO<T> {
    private T data;
    private String message;
    private boolean success;

    public static <T> ResponseDTO<T> ok(T data) {
        return ResponseDTO.<T>builder()
                .data(data)
                .success(true)
                .build();
    }

    public static <T> ResponseDTO<T> error(String message) {
        return ResponseDTO.<T>builder()
                .message(message)
                .success(false)
                .build();
    }
}
