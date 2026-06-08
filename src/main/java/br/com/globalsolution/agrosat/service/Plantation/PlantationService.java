package br.com.globalsolution.agrosat.service.Plantation;

import br.com.globalsolution.agrosat.domainmodel.Plantation;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;

public interface PlantationService {

    boolean isOwner(Long id, JwtUserData authUser);

    Plantation findById(Long id);

    Plantation create(Plantation o);

    Plantation updateById(Long id, Plantation o);

    void removeById(Long id);

}
