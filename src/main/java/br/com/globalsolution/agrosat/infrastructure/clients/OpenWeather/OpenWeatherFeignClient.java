package br.com.globalsolution.agrosat.infrastructure.clients.OpenWeather;

import java.math.BigDecimal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.globalsolution.agrosat.infrastructure.clients.OpenWeather.dto.OpenWeatherResponse;

@FeignClient(name = "openWeatherClient", url = "https://api.openweathermap.org")
public interface OpenWeatherFeignClient {

    @GetMapping("/data/2.5/forecast")
    OpenWeatherResponse getForecast(
            @RequestParam("lat") BigDecimal latitude,
            @RequestParam("lon") BigDecimal longitude,
            @RequestParam("appid") String apiKey);

}
