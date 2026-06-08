package br.com.globalsolution.agrosat.core.service.City;

import java.util.List;

import br.com.globalsolution.agrosat.core.domainmodel.City;

public interface CityService {

    List<City> findAll();

    City findById(Long id);

}
