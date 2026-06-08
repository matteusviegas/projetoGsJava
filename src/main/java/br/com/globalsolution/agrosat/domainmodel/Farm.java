package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_propriedade")
@NoArgsConstructor
@AllArgsConstructor
public class Farm {

    @Id
    @Column(name = "id_propriedade")
    private @Setter @Getter Long farmId;

    @Column(name = "nome", length = 50)
    private @Setter @Getter String name;

    @Column(name = "latitude", precision = 9, scale = 6)
    private @Setter @Getter BigDecimal latitude;

    @Column(name = "longitude", precision = 9, scale = 6)
    private @Setter @Getter BigDecimal longitude;

    @Column(name = "status", length = 1)
    private @Setter @Getter String status;

    @Column(name = "area_total")
    private @Setter @Getter BigDecimal totalArea;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_usuarios_id_usuario", referencedColumnName = "id_usuario")
    private @Setter @Getter User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "agro_cidades_id_cidade", referencedColumnName = "id_cidade")
    private @Setter @Getter City city;

    public Farm(Long farmId) {
        this.farmId = farmId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Farm farm = (Farm) o;
        return Objects.equals(farmId, farm.farmId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(farmId);
    }

}
