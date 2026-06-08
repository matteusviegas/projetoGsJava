package br.com.globalsolution.agrosat.infrastructure.clients.OpenWeather.dto;

import java.math.BigDecimal;

public record MainDTO(
        BigDecimal temp,
        BigDecimal humidity) {
}
