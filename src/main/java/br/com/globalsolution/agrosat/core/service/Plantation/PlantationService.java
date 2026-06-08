package br.com.globalsolution.agrosat.core.service.Plantation;

import java.util.List;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.Plantation;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;

public interface PlantationService {

    boolean isOwner(Long id, JwtUserData authUser);

    Plantation findById(Long id);

    List<Plantation> findAllByFarm(Farm farm);

    Plantation create(Plantation o);

    Plantation updateById(Long id, Plantation o);

    void removeById(Long id);

}
