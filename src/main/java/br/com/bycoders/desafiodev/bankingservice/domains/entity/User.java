package br.com.bycoders.desafiodev.bankingservice.domains.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "tb_users")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "user_id")
    private UUID id;

    @Column(unique = true)
    private String username;
    private String password;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tb_users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<UserRoles> roles;
}
