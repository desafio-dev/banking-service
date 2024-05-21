package br.com.bycoders.desafiodev.bankingservice.controllers;


import br.com.bycoders.desafiodev.bankingservice.domains.dtos.AuthRequestDto;
import br.com.bycoders.desafiodev.bankingservice.domains.dtos.JwtResponseDto;
import br.com.bycoders.desafiodev.bankingservice.services.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:3000")
    public JwtResponseDto AuthenticateAndGetToken(@RequestBody AuthRequestDto authRequestDTO){
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
            if(authentication.isAuthenticated()){
                return JwtResponseDto.builder()
                        .accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
            } else {
                throw new UsernameNotFoundException("invalid user request..!!");
            }
        } catch(Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }

    }
}
