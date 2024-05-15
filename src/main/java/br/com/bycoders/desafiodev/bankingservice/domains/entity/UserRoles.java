package br.com.bycoders.desafiodev.bankingservice.domains.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;

@Entity
@Table(name = "tb_roles")
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "role_id")
    private Long id;
    private String name;
}
