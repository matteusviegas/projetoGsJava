package br.com.globalsolution.agrosat.core.presentation.dto.response.User;

import br.com.globalsolution.agrosat.core.domainmodel.User;
import lombok.Builder;

@Builder
public record UserResponse(
        Long userId,
        String name,
        String email,
        String phone) {

    public static UserResponse from(User o) {
        if (o == null)
            return null;

        return UserResponse.builder()
                .userId(o.getUserId())
                .name(o.getName())
                .email(o.getEmail())
                .phone(o.getPhone())
                .build();
    }

}
