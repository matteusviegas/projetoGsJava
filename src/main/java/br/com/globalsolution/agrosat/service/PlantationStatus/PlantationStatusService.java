package br.com.globalsolution.agrosat.service.PlantationStatus;

import java.util.List;

import br.com.globalsolution.agrosat.domainmodel.PlantationStatus;

public interface PlantationStatusService {

    List<PlantationStatus> findAll();

    PlantationStatus findById(Long id);

}
