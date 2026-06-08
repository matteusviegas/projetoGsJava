package br.com.globalsolution.agrosat.service.WeatherData;

import java.util.List;

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.domainmodel.WeatherData;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;

public interface WeatherDataService {

    boolean isOwner(Long id, JwtUserData authUser);

    WeatherData findById(Long id);

    List<WeatherData> findAllByFarm(Farm farm);

    WeatherData newWeatherData(Long farmId);

    void removeById(Long id);

}
