package com.alura.foro.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
