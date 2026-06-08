package br.com.globalsolution.agrosat.presentation.dto.response.CropType;

import br.com.globalsolution.agrosat.domainmodel.CropType;
import lombok.Builder;

@Builder
public record CropTypeResponse(
        Long cropTypeId,
        String code,
        String name) {

    public static CropTypeResponse from(CropType o) {
        if (o == null)
            return null;

        return CropTypeResponse.builder()
                .cropTypeId(o.getCropTypeId())
                .code(o.getCode())
                .name(o.getName())
                .build();
    }

}
