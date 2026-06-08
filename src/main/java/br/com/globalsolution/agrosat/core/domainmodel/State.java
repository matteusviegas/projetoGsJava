package br.com.globalsolution.agrosat.core.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_estados")
@NoArgsConstructor
@AllArgsConstructor
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private @Setter @Getter Long stateId;

    @Column(name = "sigla", length = 2)
    private @Setter @Getter String acronym;

    @Column(name = "nome", length = 20)
    private @Setter @Getter String name;

    public State(Long stateId) {
        this.stateId = stateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        State state = (State) o;
        return Objects.equals(stateId, state.stateId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(stateId);
    }

}
