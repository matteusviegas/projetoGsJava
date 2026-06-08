package br.com.globalsolution.agrosat.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.City;

public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findAll();

    List<City> findAllByOrderByNameAsc();

}
