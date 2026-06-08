package br.com.globalsolution.agrosat.core.service.Farm;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.User;
import br.com.globalsolution.agrosat.core.domainmodel.repositories.FarmRepository;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.core.service.City.CityService;
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
    @Cacheable(value = "farms", key = "#id")
    public Farm findById(Long id) {
        return farmRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Fazenda não encontrada"));
    }

    @Override
    @Cacheable(value = "farms-by-user", key = "#user.userId")
    public List<Farm> findAllByUser(User user) {
        return farmRepository.findAllByUser(user);
    }

    @Override
    @CacheEvict(value = "farms-by-user", key = "#o.user.userId")
    public Farm create(Farm o) {
        cityService.findById(o.getCity().getCityId());

        return farmRepository.save(o);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "farms", key = "#id")
    }, evict = {
            @CacheEvict(value = "farms-by-user", allEntries = true)
    })
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
    @Caching(evict = {
            @CacheEvict(value = "farms", key = "#id"),
            @CacheEvict(value = "farms-by-user", allEntries = true)
    })
    public void removeById(Long id) {
        findById(id);
        farmRepository.deleteById(id);
    }

}
