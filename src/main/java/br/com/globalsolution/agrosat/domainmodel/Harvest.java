package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_produtividade")
@NoArgsConstructor
@AllArgsConstructor
public class Harvest {

    @Id
    @Column(name = "id_produtividade")
    private @Setter @Getter Long harvestId;

    @Column(name = "safra", length = 50)
    private @Setter @Getter String harvestSeason;

    @Column(name = "quantidade_kgs")
    private @Setter @Getter BigDecimal quantityInKg;

    @Column(name = "quantidade_itens")
    private @Setter @Getter Integer quantityItems;

    @Column(name = "data_colheita")
    private @Setter @Getter LocalDate harvestDate;

    @Column(name = "observacoes", length = 255)
    private @Setter @Getter String observations;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_plantacoes_id_plantacao", referencedColumnName = "id_plantacao")
    private @Setter @Getter Plantation plantation;

    public Harvest(Long harvestId) {
        this.harvestId = harvestId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Harvest harvest = (Harvest) o;
        return Objects.equals(harvestId, harvest.harvestId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(harvestId);
    }

}
