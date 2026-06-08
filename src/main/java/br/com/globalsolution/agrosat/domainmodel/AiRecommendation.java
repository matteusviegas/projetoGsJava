package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_recomendacoes_ia")
@NoArgsConstructor
@AllArgsConstructor
public class AiRecommendation {

    @Id
    @Column(name = "id_recomendacao")
    private @Setter @Getter Long recommendationId;

    @Column(name = "recomendacao", length = 355)
    private @Setter @Getter String recommendation;

    @Column(name = "prioridade")
    private @Setter @Getter Integer priority;

    @Column(name = "data_recomendacao")
    private @Setter @Getter LocalDate recommendationDate;

    @Column(name = "status", length = 1)
    private @Setter @Getter String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_plantacoes_id_plantacao", referencedColumnName = "id_plantacao")
    private @Setter @Getter Plantation plantation;

    public AiRecommendation(Long recommendationId) {
        this.recommendationId = recommendationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AiRecommendation that = (AiRecommendation) o;
        return Objects.equals(recommendationId, that.recommendationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(recommendationId);
    }

}
