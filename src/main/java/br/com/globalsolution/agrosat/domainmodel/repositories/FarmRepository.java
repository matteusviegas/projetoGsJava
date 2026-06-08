package br.com.globalsolution.agrosat.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.domainmodel.User;

public interface FarmRepository extends JpaRepository<Farm, Long> {

    List<Farm> findAllByUser(User user);

}
