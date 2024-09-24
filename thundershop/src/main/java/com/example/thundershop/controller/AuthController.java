package com.example.thundershop.controller;

import com.example.thundershop.dto.RegisterUser;
import com.example.thundershop.entity.User;
import com.example.thundershop.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Validated
public class AuthController {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public RegisterUser register(@RequestBody User user){
        User user1 = authenticationService.register(user);
        return new RegisterUser(user1.getFirstName(),
                user1.getLastName(),
                user1.getEmail(),
                user1.getPassword());
    }
}
