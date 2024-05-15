package br.com.bycoders.desafiodev.bankingservice.services;

import br.com.bycoders.desafiodev.bankingservice.domains.dtos.CreateUserDto;

public interface UserService {

    void createUser(CreateUserDto user);
}
