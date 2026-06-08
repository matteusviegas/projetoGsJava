package br.com.globalsolution.agrosat.domainmodel.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.domainmodel.CropType;

public interface CropTypeRepository extends JpaRepository<CropType, Long> {

    List<CropType> findAll();

    List<CropType> findAllByOrderByNameAsc();

}
