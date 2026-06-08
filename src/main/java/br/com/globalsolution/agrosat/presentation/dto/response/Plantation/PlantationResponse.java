package br.com.globalsolution.agrosat.presentation.dto.response.Plantation;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.globalsolution.agrosat.domainmodel.Plantation;
import lombok.Builder;

@Builder
public record PlantationResponse(
        Long plantationId,
        String name,
        LocalDate plantingDate,
        BigDecimal plantedArea,
        Long farmId,
        Long cropTypeId,
        Long plantationStatusId) {

    public static PlantationResponse from(Plantation o) {
        if (o == null) {
            return null;
        }

        return PlantationResponse.builder()
                .plantationId(o.getPlantationId())
                .name(o.getName())
                .plantingDate(o.getPlantingDate())
                .plantedArea(o.getPlantedArea())
                .farmId(o.getFarm().getFarmId())
                .cropTypeId(o.getCropType().getCropTypeId())
                .plantationStatusId(o.getPlantationStatus().getStatusId())
                .build();
    }

}
