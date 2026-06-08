package br.com.globalsolution.agrosat.service.Plantation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.domainmodel.Plantation;
import br.com.globalsolution.agrosat.domainmodel.repositories.PlantationRepository;
import br.com.globalsolution.agrosat.infrastructure.config.security.JwtUserData;
import br.com.globalsolution.agrosat.service.CropType.CropTypeService;
import br.com.globalsolution.agrosat.service.Farm.FarmService;
import br.com.globalsolution.agrosat.service.PlantationStatus.PlantationStatusService;
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
    public Plantation findById(Long id) {
        return plantationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Plantação não encontrada"));
    }

    @Override
    public Plantation create(Plantation o) {
        farmService.findById(o.getFarm().getFarmId());
        cropTypeService.findById(o.getCropType().getCropTypeId());
        plantationStatusService.findById(o.getPlantationStatus().getStatusId());

        return plantationRepository.save(o);
    }

    @Override
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
    public void removeById(Long id) {
        findById(id);
        plantationRepository.deleteById(id);
    }

}
