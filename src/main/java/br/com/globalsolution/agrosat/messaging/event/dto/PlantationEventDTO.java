package br.com.globalsolution.agrosat.messaging.event.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;

@Builder
public record PlantationEventDTO(
        Long plantationId,
        String name,
        LocalDate plantingDate,
        BigDecimal plantedArea,
        String cropTypeName,
        String statusName) {
}
