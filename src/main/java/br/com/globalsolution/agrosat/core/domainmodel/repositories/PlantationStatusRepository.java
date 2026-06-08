package br.com.globalsolution.agrosat.core.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.core.domainmodel.PlantationStatus;

public interface PlantationStatusRepository extends JpaRepository<PlantationStatus, Long> {

    List<PlantationStatus> findAll();

}
