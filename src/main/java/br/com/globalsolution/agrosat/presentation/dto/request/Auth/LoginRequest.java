package br.com.globalsolution.agrosat.presentation.dto.request.Auth;

import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "Este é um campo obrigatório") String email,
        @NotEmpty(message = "Este é um campo obrigatório") String password) {

}
