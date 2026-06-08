package br.com.globalsolution.agrosat.core.service.WeatherData;

import java.util.List;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.WeatherData;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;

public interface WeatherDataService {

    boolean isOwner(Long id, JwtUserData authUser);

    WeatherData findById(Long id);

    List<WeatherData> findAllByFarm(Farm farm);

    WeatherData newWeatherData(Long farmId);

    void removeById(Long id);

}
