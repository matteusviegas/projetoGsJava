package br.com.globalsolution.agrosat.service.Farm;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.domainmodel.Farm;
import br.com.globalsolution.agrosat.domainmodel.repositories.FarmRepository;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.service.City.CityService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {

    private final FarmRepository farmRepository;

    private final CityService cityService;

    @Override
    public boolean isOwner(Long id, JwtUserData authUser) {
        return farmRepository.findById(id)
                .map(farm -> farm.getUser()
                        .getUserId()
                        .equals(authUser.userId()))
                .orElse(false);
    }

    @Override
    public Farm findById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fazenda não encontrada"));
    }

    @Override
    public Farm create(Farm o) {
        cityService.findById(o.getCity().getCityId());

        return farmRepository.save(o);
    }

    @Override
    public Farm updateById(Long id, Farm o) {
        Farm existing = findById(id);

        cityService.findById(o.getCity().getCityId());

        existing.setName(o.getName());
        existing.setLatitude(o.getLatitude());
        existing.setLongitude(o.getLongitude());
        existing.setStatus(o.getStatus());
        existing.setTotalArea(o.getTotalArea());
        existing.setCity(o.getCity());

        return farmRepository.save(existing);
    }

    @Override
    public void removeById(Long id) {
        findById(id);
        farmRepository.deleteById(id);
    }

}
