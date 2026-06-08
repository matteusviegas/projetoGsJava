package br.com.globalsolution.agrosat.core.service.Farm;

import java.util.List;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.User;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;

public interface FarmService {

    boolean isOwner(Long id, JwtUserData authUser);

    Farm findById(Long id);

    List<Farm> findAllByUser(User user);

    Farm create(Farm o);

    Farm updateById(Long id, Farm o);

    void removeById(Long id);

}
