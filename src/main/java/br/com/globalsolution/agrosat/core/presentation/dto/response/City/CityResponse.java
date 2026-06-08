package br.com.globalsolution.agrosat.core.presentation.dto.response.City;

import br.com.globalsolution.agrosat.core.domainmodel.City;
import lombok.Builder;

@Builder
public record CityResponse(
        Long cityId,
        String name,
        Long stateId) {

    public static CityResponse from(City o) {
        if (o == null)
            return null;

        return CityResponse.builder()
                .cityId(o.getCityId())
                .name(o.getName())
                .stateId(o.getState().getStateId())
                .build();
    }

}
