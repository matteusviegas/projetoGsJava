package br.com.globalsolution.agrosat.ai.service.AiRecommendation;

import br.com.globalsolution.agrosat.ai.domainmodel.AiRecommendation;

public interface AiRecommendationService {

    AiRecommendation findById(Long id);

    AiRecommendation create(AiRecommendation o);

    void removeById(Long id);

}
