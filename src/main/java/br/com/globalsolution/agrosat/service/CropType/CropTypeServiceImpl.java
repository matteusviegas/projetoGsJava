package br.com.globalsolution.agrosat.service.CropType;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.globalsolution.agrosat.domainmodel.CropType;
import br.com.globalsolution.agrosat.domainmodel.repositories.CropTypeRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CropTypeServiceImpl implements CropTypeService {

    private final CropTypeRepository cropTypeRepository;

    @Override
    public List<CropType> findAll() {
        return cropTypeRepository.findAllByOrderByNameAsc();
    }

    @Override
    public CropType findById(Long id) {
        return cropTypeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tipo de cultura não encontrado"));
    }

}
