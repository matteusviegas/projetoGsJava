package br.com.globalsolution.agrosat.infrastructure.clients.OpenWeather.dto;

import java.util.List;

public record OpenWeatherResponse(
                List<ForecastDTO> list) {
}
