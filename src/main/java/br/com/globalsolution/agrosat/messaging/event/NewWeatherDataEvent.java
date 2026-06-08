package br.com.globalsolution.agrosat.messaging.event;

import java.math.BigDecimal;
import java.util.List;

import br.com.globalsolution.agrosat.messaging.event.dto.FarmEventDTO;
import br.com.globalsolution.agrosat.messaging.event.dto.PlantationEventDTO;
import lombok.Builder;

@Builder
public record NewWeatherDataEvent(
        Long weatherDataId,
        FarmEventDTO farm,
        List<PlantationEventDTO> plantations,
        BigDecimal temperature,
        BigDecimal humidity,
        BigDecimal windSpeed,
        String description) {
}
