package com.crud_spring_boot.crud_spring_boot.application.service;

import com.crud_spring_boot.crud_spring_boot.application.dto.LoginUserDTO;
import com.crud_spring_boot.crud_spring_boot.application.dto.RegisterUserDTO;
import com.crud_spring_boot.crud_spring_boot.domain.model.User;
import com.crud_spring_boot.crud_spring_boot.domain.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ){
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegisterUserDTO input){
        User user = new User();

        user.setUsername(input.getUsername());
        user.setPassword(passwordEncoder.encode((input.getPassword())));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDTO input){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );

        return userRepository.findByUsername(input.getUsername()).orElseThrow();
    }
}
