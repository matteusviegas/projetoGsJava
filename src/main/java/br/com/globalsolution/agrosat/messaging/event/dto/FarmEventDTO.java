package br.com.globalsolution.agrosat.messaging.event.dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record FarmEventDTO(
        Long farmId,
        String name,
        BigDecimal latitude,
        BigDecimal longitude,
        BigDecimal totalArea,
        String cityName,
        String stateName) {
}
