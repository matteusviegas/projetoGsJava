package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_plantacoes")
@NoArgsConstructor
@AllArgsConstructor
public class Plantation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_plantacao")
    private @Setter @Getter Long plantationId;

    @Column(name = "nome", length = 50)
    private @Setter @Getter String name;

    @Column(name = "data_plantio")
    private @Setter @Getter LocalDate plantingDate;

    @Column(name = "area_plantada")
    private @Setter @Getter BigDecimal plantedArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_propriedade_id_propriedade", referencedColumnName = "id_propriedade")
    private @Setter @Getter Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_tipo_cultura_id_tipo_cultura", referencedColumnName = "id_tipo_cultura")
    private @Setter @Getter CropType cropType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_plantacoes_status_id_status", referencedColumnName = "id_status")
    private @Setter @Getter PlantationStatus plantationStatus;

    public Plantation(Long plantationId) {
        this.plantationId = plantationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Plantation that = (Plantation) o;
        return Objects.equals(plantationId, that.plantationId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(plantationId);
    }

}
