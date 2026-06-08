package br.com.globalsolution.agrosat.domainmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.WeatherData;

public interface WeatherDataRepository extends JpaRepository<WeatherData, Long> {
}
