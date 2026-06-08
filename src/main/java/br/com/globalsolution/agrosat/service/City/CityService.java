package br.com.globalsolution.agrosat.service.City;

import java.util.List;

import br.com.globalsolution.agrosat.domainmodel.City;

public interface CityService {

    List<City> findAll();

    City findById(Long id);

}
