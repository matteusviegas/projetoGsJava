package br.com.globalsolution.agrosat.core.service.City;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.core.domainmodel.City;
import br.com.globalsolution.agrosat.core.domainmodel.repositories.CityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    @Cacheable(value = "cities")
    public List<City> findAll() {
        return cityRepository.findAllByOrderByNameAsc();
    }

    @Override
    @Cacheable(value = "cities", key = "#id")
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cidade não encontrada"));
    }

}
