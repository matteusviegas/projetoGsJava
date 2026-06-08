package br.com.globalsolution.agrosat.service.Farm;

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;

public interface FarmService {

    boolean isOwner(Long id, JwtUserData authUser);

    Farm findById(Long id);

    Farm create(Farm o);

    Farm updateById(Long id, Farm o);

    void removeById(Long id);

}
