package br.com.globalsolution.agrosat.core.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_sensores")
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private @Setter @Getter Long sensorId;

    @Column(name = "latitude", precision = 9, scale = 6)
    private @Setter @Getter BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private @Setter @Getter BigDecimal longitude;

    @Column(name = "status", length = 1)
    private @Setter @Getter String status;

    @Column(name = "data_instalacao")
    private @Setter @Getter LocalDate installationDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_plantacoes_id_plantacao", referencedColumnName = "id_plantacao")
    private @Setter @Getter Plantation plantation;

    public Sensor(Long sensorId) {
        this.sensorId = sensorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Sensor sensor = (Sensor) o;
        return Objects.equals(sensorId, sensor.sensorId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(sensorId);
    }

}
