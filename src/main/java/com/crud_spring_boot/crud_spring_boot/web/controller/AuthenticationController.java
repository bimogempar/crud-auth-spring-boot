package com.crud_spring_boot.crud_spring_boot.web.controller;

import com.crud_spring_boot.crud_spring_boot.application.dto.LoginUserDTO;
import com.crud_spring_boot.crud_spring_boot.application.dto.RegisterUserDTO;
import com.crud_spring_boot.crud_spring_boot.application.service.AuthenticationService;
import com.crud_spring_boot.crud_spring_boot.application.service.JwtService;
import com.crud_spring_boot.crud_spring_boot.domain.model.User;
import com.crud_spring_boot.crud_spring_boot.web.rest.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(
            JwtService jwtService,
            AuthenticationService authenticationService
    ){
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegisterUserDTO registerUserDTO){
        User registeredUser = authenticationService.signup(registerUserDTO);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDTO loginUserDTO){
        User authenticatedUser = authenticationService.authenticate(loginUserDTO);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
