package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_cidades")
@NoArgsConstructor
@AllArgsConstructor
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cidade")
    private @Setter @Getter Long cityId;

    @Column(name = "nome", length = 100)
    private @Setter @Getter String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_estados_id_estado", referencedColumnName = "id_estado")
    private @Setter @Getter State state;

    public City(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        City city = (City) o;
        return Objects.equals(cityId, city.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cityId);
    }

}
