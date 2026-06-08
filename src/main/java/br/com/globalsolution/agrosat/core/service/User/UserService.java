package br.com.globalsolution.agrosat.core.service.User;

import br.com.globalsolution.agrosat.core.domainmodel.User;

public interface UserService {

    User findById(Long id);

    User findByEmail(String email);

    User create(User o);

    User updateById(Long id, String actualPassword, User o);

    void removeById(Long id);

}
