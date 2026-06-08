package br.com.globalsolution.agrosat.presentation.dto.request.User;

import br.com.globalsolution.agrosat.domainmodel.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;

@Builder
public record CreateUserRequest(
        @NotEmpty(message = "Este é um campo obrigatório") String name,
        @NotEmpty(message = "Este é um campo obrigatório") String email,
        @NotEmpty(message = "Este é um campo obrigatório") String password,
        @NotEmpty(message = "Este é um campo obrigatório") String phone) {

    public static User to(CreateUserRequest dto) {
        if (dto == null)
            return null;

        return User.builder()
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .phone(dto.phone())
                .build();
    }

}
