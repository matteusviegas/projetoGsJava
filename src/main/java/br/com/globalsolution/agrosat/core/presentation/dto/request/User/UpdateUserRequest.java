package br.com.globalsolution.agrosat.core.presentation.dto.request.User;

import br.com.globalsolution.agrosat.core.domainmodel.User;
import jakarta.validation.constraints.NotEmpty;

public record UpdateUserRequest(
        @NotEmpty(message = "Este é um campo obrigatório") String name,
        @NotEmpty(message = "Este é um campo obrigatório") String email,
        @NotEmpty(message = "Este é um campo obrigatório") String actualPassword,
        @NotEmpty(message = "Este é um campo obrigatório") String newPassword,
        @NotEmpty(message = "Este é um campo obrigatório") String phone) {

    public static User to(UpdateUserRequest dto) {
        if (dto == null)
            return null;

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.newPassword())
                .phone(dto.phone())
                .build();
    }
}
