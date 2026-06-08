package br.com.globalsolution.agrosat.core.service.Plantation;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.core.domainmodel.Farm;
import br.com.globalsolution.agrosat.core.domainmodel.Plantation;
import br.com.globalsolution.agrosat.core.domainmodel.repositories.PlantationRepository;
import br.com.globalsolution.agrosat.core.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.core.service.CropType.CropTypeService;
import br.com.globalsolution.agrosat.core.service.Farm.FarmService;
import br.com.globalsolution.agrosat.core.service.PlantationStatus.PlantationStatusService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantationServiceImpl implements PlantationService {

    private final PlantationRepository plantationRepository;

    private final FarmService farmService;

    private final CropTypeService cropTypeService;

    private final PlantationStatusService plantationStatusService;

    @Override
    public boolean isOwner(Long id, JwtUserData authUser) {
        return plantationRepository.findById(id)
                .map(plantation -> plantation.getFarm()
                        .getUser()
                        .getUserId()
                        .equals(authUser.userId()))
                .orElse(false);
    }

    @Override
    @Cacheable(value = "plantations", key = "#id")
    public Plantation findById(Long id) {
        return plantationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada"));
    }

    @Override
    @Cacheable(value = "plantations-by-farm", key = "#farm.farmId")
    public List<Plantation> findAllByFarm(Farm farm) {
        return plantationRepository.findAllByFarm(farm);
    }

    @Override
    @CacheEvict(value = "plantations-by-farm", key = "#o.farm.farmId")
    public Plantation create(Plantation o) {
        farmService.findById(o.getFarm().getFarmId());
        cropTypeService.findById(o.getCropType().getCropTypeId());
        plantationStatusService.findById(o.getPlantationStatus().getStatusId());

        return plantationRepository.save(o);
    }

    @Override
    @Caching(put = {
            @CachePut(value = "plantations", key = "#id")
    }, evict = {
            @CacheEvict(value = "plantations-by-farm", allEntries = true)
    })
    public Plantation updateById(Long id, Plantation o) {
        Plantation existing = findById(id);

        cropTypeService.findById(o.getCropType().getCropTypeId());
        plantationStatusService.findById(o.getPlantationStatus().getStatusId());

        existing.setName(o.getName());
        existing.setPlantingDate(o.getPlantingDate());
        existing.setPlantedArea(o.getPlantedArea());
        existing.setFarm(o.getFarm());
        existing.setCropType(o.getCropType());
        existing.setPlantationStatus(o.getPlantationStatus());

        return plantationRepository.save(existing);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "plantations", key = "#id"),
            @CacheEvict(value = "plantations-by-farm", allEntries = true)
    })
    public void removeById(Long id) {
        findById(id);
        plantationRepository.deleteById(id);
    }

}
