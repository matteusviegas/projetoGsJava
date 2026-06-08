package br.com.globalsolution.agrosat.domainmodel;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Builder
@Table(name = "agro_usuarios")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name = "id_usuario")
    private @Setter @Getter Long userId;

    @Column(name = "nome", length = 50)
    private @Setter @Getter String name;

    @Column(name = "email", length = 50)
    private @Setter @Getter String email;

    @Column(name = "senha", length = 100)
    private @Setter @Getter String password;

    @Column(name = "telefone", length = 50)
    private @Setter @Getter String phone;

    @Column(name = "data_cadastro")
    private @Setter @Getter LocalDate registrationDate;

    public User(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(userId);
    }

}
