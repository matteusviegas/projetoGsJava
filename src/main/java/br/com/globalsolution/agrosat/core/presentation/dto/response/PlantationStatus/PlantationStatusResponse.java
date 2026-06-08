package br.com.globalsolution.agrosat.core.presentation.dto.response.PlantationStatus;

import br.com.globalsolution.agrosat.core.domainmodel.PlantationStatus;
import lombok.Builder;

@Builder
public record PlantationStatusResponse(
        Long plantationStatusId,
        Integer code,
        String statusType) {

    public static PlantationStatusResponse from(PlantationStatus o) {
        if (o == null)
            return null;

        return PlantationStatusResponse.builder()
                .plantationStatusId(o.getStatusId())
                .code(o.getCode())
                .statusType(o.getStatusType())
                .build();
    }

}
