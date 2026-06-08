package br.com.globalsolution.agrosat.core.presentation.dto.request.Plantation;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.globalsolution.agrosat.core.domainmodel.CropType;
import br.com.globalsolution.agrosat.core.domainmodel.Plantation;
import br.com.globalsolution.agrosat.core.domainmodel.PlantationStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePlantationRequest {

    @NotBlank(message = "Este é um campo obrigatório")
    @Size(max = 50, message = "O nome deve possuir no máximo 50 caracteres")
    private String name;

    @NotNull(message = "Este é um campo obrigatório")
    private LocalDate plantingDate;

    @NotNull(message = "Este é um campo obrigatório")
    @Positive(message = "A área plantada deve ser maior que zero")
    private BigDecimal plantedArea;

    @NotNull(message = "Este é um campo obrigatório")
    private Long cropTypeId;

    @NotNull(message = "Este é um campo obrigatório")
    private Long plantationStatusId;

    public static Plantation to(UpdatePlantationRequest dto) {
        if (dto == null)
            return null;

        return Plantation.builder()
                .name(dto.getName())
                .plantingDate(dto.getPlantingDate())
                .plantedArea(dto.getPlantedArea())
                .cropType(new CropType(dto.getCropTypeId()))
                .plantationStatus(new PlantationStatus(dto.getPlantationStatusId()))
                .build();
    }

}
