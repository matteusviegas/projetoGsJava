package br.com.globalsolution.agrosat.core.presentation.dto.response.State;

import br.com.globalsolution.agrosat.core.domainmodel.State;
import lombok.Builder;

@Builder
public record StateResponse(
        Long stateId,
        String acronym,
        String name) {

    public static StateResponse from(State o) {
        if (o == null)
            return null;

        return StateResponse.builder()
                .stateId(o.getStateId())
                .acronym(o.getAcronym())
                .name(o.getName())
                .build();
    }

}
