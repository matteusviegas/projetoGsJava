package br.com.globalsolution.agrosat.presentation.dto.request.Farm;

import java.math.BigDecimal;

import br.com.globalsolution.agrosat.domainmodel.City;
import br.com.globalsolution.agrosat.domainmodel.Farm;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateFarmRequest {

    @NotBlank(message = "Este é um campo obrigatório")
    @Size(max = 50, message = "O nome deve possuir no máximo 50 caracteres")
    private String name;

    @NotNull(message = "Este é um campo obrigatório")
    @DecimalMin(value = "-90.0", message = "A latitude deve estar entre -90 e 90")
    @DecimalMax(value = "90.0", message = "A latitude deve estar entre -90 e 90")
    private BigDecimal latitude;

    @NotNull(message = "Este é um campo obrigatório")
    @DecimalMin(value = "-180.0", message = "A longitude deve estar entre -180 e 180")
    @DecimalMax(value = "180.0", message = "A longitude deve estar entre -180 e 180")
    private BigDecimal longitude;

    @NotBlank(message = "Este é um campo obrigatório")
    @Pattern(regexp = "A|I", message = "O status deve ser A (Ativo) ou I (Inativo)")
    private String status;

    @NotNull(message = "Este é um campo obrigatório")
    @Positive(message = "A área total deve ser maior que zero")
    private BigDecimal totalArea;

    @NotNull(message = "Este é um campo obrigatório")
    private Long cityId;

    public static Farm to(UpdateFarmRequest dto) {
        if (dto == null)
            return null;

        return Farm.builder()
                .name(dto.getName())
                .latitude(dto.getLatitude())
                .longitude(dto.getLongitude())
                .status(dto.getStatus())
                .totalArea(dto.getTotalArea())
                .city(new City(dto.getCityId()))
                .build();
    }

}
