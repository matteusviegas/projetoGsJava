package br.com.globalsolution.agrosat.service.City;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Cidade não encontrada"));
    }

}
