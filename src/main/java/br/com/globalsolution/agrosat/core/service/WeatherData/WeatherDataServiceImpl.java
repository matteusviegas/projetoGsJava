package br.com.globalsolution.agrosat.core.service.WeatherData;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.WeatherData;
import br.com.globalsolution.agrosat.core.domainmodel.repositories.WeatherDataRepository;
import br.com.globalsolution.agrosat.core.infrastructure.clients.OpenWeather.OpenWeatherFeignClient;
import br.com.globalsolution.agrosat.core.infrastructure.clients.OpenWeather.dto.ForecastDTO;
import br.com.globalsolution.agrosat.core.infrastructure.clients.OpenWeather.dto.OpenWeatherResponse;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.core.infrastructure.producer.WeatherDataProducer;
import br.com.globalsolution.agrosat.core.service.Farm.FarmService;
import br.com.globalsolution.agrosat.core.service.Plantation.PlantationService;
import br.com.globalsolution.agrosat.messaging.event.NewWeatherDataEvent;
import br.com.globalsolution.agrosat.messaging.event.dto.FarmEventDTO;
import br.com.globalsolution.agrosat.messaging.event.dto.PlantationEventDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WeatherDataServiceImpl implements WeatherDataService {

    @Value("${openweather.api-key}")
    private String apiKey;

    private final WeatherDataRepository weatherDataRepository;

    private final FarmService farmService;

    private final PlantationService plantationService;

    private final OpenWeatherFeignClient openWeatherFeignClient;

    private final WeatherDataProducer weatherDataProducer;

    private void publishNewWeatherDataEvent(WeatherData weatherData) {
        Farm farm = weatherData.getFarm();

        List<PlantationEventDTO> plantations = plantationService.findAllByFarm(farm)
                .stream()
                .map(p -> PlantationEventDTO.builder()
                        .plantationId(p.getPlantationId())
                        .name(p.getName())
                        .plantingDate(p.getPlantingDate())
                        .plantedArea(p.getPlantedArea())
                        .cropTypeName(p.getCropType().getName())
                        .statusName(p.getPlantationStatus().getStatusType())
                        .build())
                .toList();

        weatherDataProducer.publish(
                NewWeatherDataEvent.builder()
                        .weatherDataId(weatherData.getWeatherDataId())
                        .farm(FarmEventDTO.builder()
                                .farmId(farm.getFarmId())
                                .name(farm.getName())
                                .latitude(farm.getLatitude())
                                .longitude(farm.getLongitude())
                                .totalArea(farm.getTotalArea())
                                .cityName(farm.getCity().getName())
                                .stateName(farm.getCity().getState().getName())
                                .build())
                        .plantations(plantations)
                        .temperature(weatherData.getTemperature())
                        .humidity(weatherData.getHumidity())
                        .windSpeed(weatherData.getWindSpeed())
                        .description(weatherData.getDescription())
                        .build());
    }

    @Override
    public boolean isOwner(Long id, JwtUserData authUser) {
        return weatherDataRepository.findById(id)
                .map(farm -> farm.getFarm()
                        .getUser()
                        .getUserId()
                        .equals(authUser.userId()))
                .orElse(false);
    }

    @Override
    @Cacheable(value = "weather-datas", key = "#id")
    public WeatherData findById(Long id) {
        return weatherDataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Dado climático não encontrado"));
    }

    @Override
    @Cacheable(value = "weather-datas-by-farm", key = "#farm.farmId")
    public List<WeatherData> findAllByFarm(Farm farm) {
        return weatherDataRepository.findAllByFarm(farm);
    }

    @Override
    @CacheEvict(value = "weather-datas-by-farm", key = "#farmId")
    public WeatherData newWeatherData(Long farmId) {
        Farm farm = farmService.findById(farmId);

        OpenWeatherResponse response = openWeatherFeignClient.getForecast(
                farm.getLatitude(),
                farm.getLongitude(),
                apiKey);

        ForecastDTO forecast = response.list().get(0);

        WeatherData weatherData = WeatherData.builder()
                .temperature(forecast.main().temp())
                .humidity(forecast.main().humidity())
                .windSpeed(forecast.wind().speed())
                .description(forecast.weather().get(0).description())
                .dateTime(LocalDateTime.now())
                .farm(farm)
                .build();

        WeatherData saved = weatherDataRepository.save(weatherData);

        publishNewWeatherDataEvent(saved);

        return saved;
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "weather-datas", key = "#id"),
            @CacheEvict(value = "weather-datas-by-farm", allEntries = true)
    })
    public void removeById(Long id) {
        findById(id);
        weatherDataRepository.deleteById(id);
    }

}
