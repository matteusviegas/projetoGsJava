package br.com.globalsolution.agrosat.core.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.Plantation;

public interface PlantationRepository extends JpaRepository<Plantation, Long> {

    List<Plantation> findAllByFarm(Farm farm);

}
