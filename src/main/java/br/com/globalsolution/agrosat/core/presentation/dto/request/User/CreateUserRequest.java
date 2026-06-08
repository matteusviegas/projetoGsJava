package br.com.globalsolution.agrosat.core.presentation.dto.request.User;

import br.com.globalsolution.agrosat.core.domainmodel.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserRequest {

    @NotBlank(message = "Este é um campo obrigatório")
    @Size(min = 2, max = 50, message = "O nome deve possuir entre 2 e 50 caracteres")
    private String name;

    @NotBlank(message = "Este é um campo obrigatório")
    @Email(message = "Email inválido")
    @Size(max = 50, message = "O email deve possuir no máximo 50 caracteres")
    private String email;

    @NotBlank(message = "Este é um campo obrigatório")
    @Size(min = 6, max = 100, message = "A senha deve possuir entre 6 e 100 caracteres")
    private String password;

    @NotBlank(message = "Este é um campo obrigatório")
    @Pattern(regexp = "^\\d{10,15}$", message = "O telefone deve conter entre 10 e 15 dígitos numéricos")
    private String phone;

    public static User to(CreateUserRequest dto) {
        if (dto == null) {
            return null;
        }

        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .phone(dto.getPhone())
                .build();
    }

}
