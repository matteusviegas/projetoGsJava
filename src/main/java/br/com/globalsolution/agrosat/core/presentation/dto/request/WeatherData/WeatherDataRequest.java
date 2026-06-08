package br.com.globalsolution.agrosat.core.presentation.dto.request.WeatherData;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class WeatherDataRequest {

    @NotNull(message = "Este é um campo obrigatório")
    private Long farmId;

}
