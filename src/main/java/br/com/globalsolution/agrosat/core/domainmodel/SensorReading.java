package br.com.globalsolution.agrosat.core.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_leitura_sensores")
@NoArgsConstructor
@AllArgsConstructor
public class SensorReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leitura")
    private @Setter @Getter Long readingId;

    @Column(name = "temperatura")
    private @Setter @Getter BigDecimal temperature;

    @Column(name = "umidade")
    private @Setter @Getter BigDecimal humidity;

    @Column(name = "ph_solo")
    private @Setter @Getter BigDecimal soilPh;

    @Column(name = "data_hora")
    private @Setter @Getter LocalDateTime readingDateTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_sensores_id_sensor", referencedColumnName = "id_sensor")
    private @Setter @Getter Sensor sensor;

    public SensorReading(Long readingId) {
        this.readingId = readingId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        SensorReading that = (SensorReading) o;
        return Objects.equals(readingId, that.readingId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(readingId);
    }

}
