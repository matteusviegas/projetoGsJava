package br.com.globalsolution.agrosat.core.service.PlantationStatus;

import java.util.List;

import br.com.globalsolution.agrosat.core.domainmodel.PlantationStatus;

public interface PlantationStatusService {

    List<PlantationStatus> findAll();

    PlantationStatus findById(Long id);

}
