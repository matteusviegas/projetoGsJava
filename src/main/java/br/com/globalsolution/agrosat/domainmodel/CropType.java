package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_tipo_cultura")
@NoArgsConstructor
@AllArgsConstructor
public class CropType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_cultura")
    private @Setter @Getter Long cropTypeId;

    @Column(name = "code", length = 20)
    private @Setter @Getter String code;

    @Column(name = "nome", length = 50)
    private @Setter @Getter String name;

    public CropType(Long cropTypeId) {
        this.cropTypeId = cropTypeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CropType cropType = (CropType) o;
        return Objects.equals(cropTypeId, cropType.cropTypeId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cropTypeId);
    }

}
