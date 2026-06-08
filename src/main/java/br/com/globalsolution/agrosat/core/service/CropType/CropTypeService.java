package br.com.globalsolution.agrosat.core.service.CropType;

import java.util.List;

import br.com.globalsolution.agrosat.core.domainmodel.CropType;

public interface CropTypeService {

    List<CropType> findAll();

    CropType findById(Long id);

}
