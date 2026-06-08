package br.com.globalsolution.agrosat.infrastructure.config.security;

import lombok.Builder;

@Builder
public record JwtUserData(Long userId, String email) {
}
