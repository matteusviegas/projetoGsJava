package br.com.globalsolution.agrosat.ai.domainmodel.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.globalsolution.agrosat.ai.domainmodel.AiRecommendation;

public interface AiRecommendationRepository extends JpaRepository<AiRecommendation, Long> {
}
