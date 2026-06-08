package br.com.globalsolution.agrosat.domainmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.Plantation;

public interface PlantationRepository extends JpaRepository<Plantation, Long> {
}
