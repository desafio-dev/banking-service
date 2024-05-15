package br.com.bycoders.desafiodev.bankingservice.controllers;

import br.com.bycoders.desafiodev.bankingservice.domains.dtos.CreateUserDto;
import br.com.bycoders.desafiodev.bankingservice.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDto user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }
}
