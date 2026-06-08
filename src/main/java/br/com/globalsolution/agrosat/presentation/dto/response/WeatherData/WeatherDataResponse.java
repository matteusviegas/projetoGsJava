package br.com.globalsolution.agrosat.presentation.dto.response.WeatherData;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import br.com.globalsolution.agrosat.domainmodel.WeatherData;
import lombok.Builder;

@Builder
public record WeatherDataResponse(
        Long weatherDataId,
        BigDecimal temperature,
        BigDecimal humidity,
        BigDecimal rainProbability,
        BigDecimal windSpeed,
        LocalDateTime dateTime,
        String description,
        Long farmId) {

    public static WeatherDataResponse from(WeatherData o) {
        if (o == null) {
            return null;
        }

        return WeatherDataResponse.builder()
                .weatherDataId(o.getWeatherDataId())
                .temperature(o.getTemperature())
                .humidity(o.getHumidity())
                .rainProbability(o.getRainProbability())
                .windSpeed(o.getWindSpeed())
                .dateTime(o.getDateTime())
                .description(o.getDescription())
                .farmId(o.getFarm().getFarmId())
                .build();
    }

}
