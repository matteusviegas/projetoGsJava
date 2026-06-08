package br.com.globalsolution.agrosat.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.State;

public interface StateRepository extends JpaRepository<State, Long> {

    List<State> findAll();

    List<State> findAllByOrderByNameAsc();

}
