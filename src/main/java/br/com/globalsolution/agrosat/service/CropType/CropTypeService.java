package br.com.globalsolution.agrosat.service.CropType;

import java.util.List;

import br.com.globalsolution.agrosat.domainmodel.CropType;

public interface CropTypeService {

    List<CropType> findAll();

    CropType findById(Long id);

}
