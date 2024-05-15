package br.com.bycoders.desafiodev.bankingservice.repositories;

import br.com.bycoders.desafiodev.bankingservice.domains.entity.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<UserRoles, Long> {

    UserRoles findByName(String name);

}

