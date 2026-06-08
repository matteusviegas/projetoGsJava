package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_plantacoes_status")
@NoArgsConstructor
@AllArgsConstructor
public class PlantationStatus {

    @Id
    @Column(name = "id_status")
    private @Setter @Getter Long statusId;

    @Column(name = "code")
    private @Setter @Getter Integer code;

    @Column(name = "tipo_status", length = 50)
    private @Setter @Getter String statusType;

    public PlantationStatus(Long statusId) {
        this.statusId = statusId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PlantationStatus that = (PlantationStatus) o;
        return Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(statusId);
    }

}
