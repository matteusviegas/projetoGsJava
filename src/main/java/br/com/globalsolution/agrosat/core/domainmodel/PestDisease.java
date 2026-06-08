package br.com.globalsolution.agrosat.core.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_pragas_doencas")
@NoArgsConstructor
@AllArgsConstructor
public class PestDisease {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_praga")
    private @Setter @Getter Long pestDiseaseId;

    @Column(name = "nome", length = 50)
    private @Setter @Getter String name;

    @Column(name = "sintomas", length = 255)
    private @Setter @Getter String symptoms;

    @Column(name = "nivel_risco")
    private @Setter @Getter Integer riskLevel;

    @Column(name = "data_deteccao")
    private @Setter @Getter LocalDate detectionDate;

    @Column(name = "status", length = 1)
    private @Setter @Getter String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_plantacoes_id_plantacao", referencedColumnName = "id_plantacao")
    private @Setter @Getter Plantation plantation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_leitura_sensores_id_leitura", referencedColumnName = "id_leitura")
    private @Setter @Getter SensorReading sensorReading;

    public PestDisease(Long pestDiseaseId) {
        this.pestDiseaseId = pestDiseaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        PestDisease that = (PestDisease) o;
        return Objects.equals(pestDiseaseId, that.pestDiseaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pestDiseaseId);
    }

}
