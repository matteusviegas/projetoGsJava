package br.com.globalsolution.agrosat.core.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findAllByFarm(Farm farm);

}
