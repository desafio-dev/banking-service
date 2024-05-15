package br.com.bycoders.desafiodev.bankingservice.services.impl;

import br.com.bycoders.desafiodev.bankingservice.domains.dtos.CreateUserDto;
import br.com.bycoders.desafiodev.bankingservice.domains.entity.User;
import br.com.bycoders.desafiodev.bankingservice.domains.enums.RolesEnum;
import br.com.bycoders.desafiodev.bankingservice.repositories.RoleRepository;
import br.com.bycoders.desafiodev.bankingservice.repositories.UserRepository;
import br.com.bycoders.desafiodev.bankingservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public void createUser(CreateUserDto user) {

        var role = roleRepository.findByName(RolesEnum.BASIC.name());

        userRepository.save(User.builder()
                .username(user.username())
                .password(passwordEncoder.encode(user.password()))
                .roles(Set.of(role))
                .build());
    }
}
