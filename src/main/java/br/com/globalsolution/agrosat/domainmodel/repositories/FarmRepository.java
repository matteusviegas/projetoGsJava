package br.com.globalsolution.agrosat.domainmodel.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.Farm;

public interface FarmRepository extends JpaRepository<Farm, Long> {
}
