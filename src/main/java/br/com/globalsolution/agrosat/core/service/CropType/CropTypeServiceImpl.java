package br.com.globalsolution.agrosat.core.service.CropType;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.core.domainmodel.CropType;
import br.com.globalsolution.agrosat.core.domainmodel.repositories.CropTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropTypeServiceImpl implements CropTypeService {

    private final CropTypeRepository cropTypeRepository;

    @Override
    @Cacheable(value = "crop-types")
    public List<CropType> findAll() {
        return cropTypeRepository.findAllByOrderByNameAsc();
    }

    @Override
    @Cacheable(value = "crop-types", key = "#id")
    public CropType findById(Long id) {
        return cropTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tipo de cultura não encontrado"));
    }

}
