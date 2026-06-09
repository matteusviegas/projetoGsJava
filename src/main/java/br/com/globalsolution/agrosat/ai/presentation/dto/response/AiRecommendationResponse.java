package br.com.globalsolution.agrosat.ai.presentation.dto.response;

import java.time.LocalDate;

import br.com.globalsolution.agrosat.ai.domainmodel.AiRecommendation;
import lombok.Builder;

@Builder
public record AiRecommendationResponse(
        Long recommendationId,
        String recommendation,
        Integer priority,
        LocalDate recommendationDate,
        String status,
        Long plantationId) {

    public static AiRecommendationResponse from(AiRecommendation o) {
        if (o == null)
            return null;

        return AiRecommendationResponse.builder()
                .recommendationId(o.getRecommendationId())
                .recommendation(o.getRecommendation())
                .priority(o.getPriority())
                .recommendationDate(o.getRecommendationDate())
                .status(o.getStatus())
                .plantationId(o.getPlantationId())
                .build();
    }

}
