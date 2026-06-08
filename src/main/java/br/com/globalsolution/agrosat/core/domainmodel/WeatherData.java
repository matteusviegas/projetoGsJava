package br.com.globalsolution.agrosat.core.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_dados_climaticos")
@NoArgsConstructor
@AllArgsConstructor
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_clima")
    private @Setter @Getter Long weatherDataId;

    @Column(name = "temperatura")
    private @Setter @Getter BigDecimal temperature;

    @Column(name = "umidade")
    private @Setter @Getter BigDecimal humidity;

    @Column(name = "chance_chuva")
    private @Setter @Getter BigDecimal rainProbability;

    @Column(name = "velocidade_vento")
    private @Setter @Getter BigDecimal windSpeed;

    @Column(name = "data_hora")
    private @Setter @Getter LocalDateTime dateTime;

    @Column(name = "descricao", length = 250)
    private @Setter @Getter String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_propriedade_id_propriedade", referencedColumnName = "id_propriedade")
    private @Setter @Getter Farm farm;

    public WeatherData(Long weatherDataId) {
        this.weatherDataId = weatherDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        WeatherData that = (WeatherData) o;
        return Objects.equals(weatherDataId, that.weatherDataId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(weatherDataId);
    }

}
