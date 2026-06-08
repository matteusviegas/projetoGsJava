package br.com.globalsolution.agrosat.core.infrastructure.clients.OpenWeather.dto;

import java.math.BigDecimal;
import java.util.List;

public record ForecastDTO(
                MainDTO main,
                List<WeatherDTO> weather,
                WindDTO wind,
                BigDecimal pop) {
}
