package br.com.globalsolution.agrosat.core.presentation.dto.response.Farm;

import java.math.BigDecimal;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import lombok.Builder;

@Builder
public record FarmResponse(
        Long farmId,
        String name,
        BigDecimal latitude,
        BigDecimal longitude,
        String status,
        BigDecimal totalArea,
        Long userId,
        Long cityId) {

    public static FarmResponse from(Farm o) {
        if (o == null)
            return null;

        return FarmResponse.builder()
                .farmId(o.getFarmId())
                .name(o.getName())
                .latitude(o.getLatitude())
                .longitude(o.getLongitude())
                .status(o.getStatus())
                .totalArea(o.getTotalArea())
                .userId(o.getUser().getUserId())
                .cityId(o.getCity().getCityId())
                .build();
    }

}
