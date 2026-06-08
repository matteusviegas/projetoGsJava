package br.com.globalsolution.agrosat.service.PlantationStatus;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.domainmodel.PlantationStatus;
import br.com.globalsolution.agrosat.domainmodel.repositories.PlantationStatusRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlantationStatusServiceImpl implements PlantationStatusService {

    private final PlantationStatusRepository plantationStatusRepository;

    @Override
    public List<PlantationStatus> findAll() {
        return plantationStatusRepository.findAll();
    }

    @Override
    public PlantationStatus findById(Long id) {
        return plantationStatusRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Status de plantação não encontrado"));
    }

}
