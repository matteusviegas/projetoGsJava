package br.com.globalsolution.agrosat.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.domainmodel.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {

    List<WeatherData> findAllByFarm(Farm farm);

}
