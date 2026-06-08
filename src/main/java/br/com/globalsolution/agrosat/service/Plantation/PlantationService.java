package br.com.globalsolution.agrosat.service.Plantation;

import java.util.List;

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.domainmodel.Plantation;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;

public interface PlantationService {

    boolean isOwner(Long id, JwtUserData authUser);

    Plantation findById(Long id);

    List<Plantation> findAllByFarm(Farm farm);

    Plantation create(Plantation o);

    Plantation updateById(Long id, Plantation o);

    void removeById(Long id);

}
