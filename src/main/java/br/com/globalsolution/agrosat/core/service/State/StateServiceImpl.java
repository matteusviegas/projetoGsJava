package br.com.globalsolution.agrosat.core.service.State;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.globalsolution.agrosat.core.domainmodel.State;
import br.com.globalsolution.agrosat.core.domainmodel.repositories.StateRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StateServiceImpl implements StateService {

    private final StateRepository stateRepository;

    @Override
    @Cacheable(value = "states")
    public List<State> findAll() {
        return stateRepository.findAllByOrderByNameAsc();
    }

}
