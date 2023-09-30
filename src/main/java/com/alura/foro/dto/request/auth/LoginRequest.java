package com.alura.foro.dto.request.auth;

public record LoginRequest(
        String username,
        String password
) {
}
