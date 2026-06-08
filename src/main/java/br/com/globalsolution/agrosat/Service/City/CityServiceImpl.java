package br.com.globalsolution.agrosat.Service.City;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.globalsolution.agrosat.domainmodel.City;
import br.com.globalsolution.agrosat.domainmodel.repositories.CityRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        return cityRepository.findAllByOrderByNameAsc();
    }

}
