package com.blue.iotapp.controller;

import com.blue.iotapp.model.User;
import com.blue.iotapp.payload.LoginRequest;
import com.blue.iotapp.payload.SignUpRequest;
import com.blue.iotapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping()
public class RegistrationController {

    private AuthenticationManager authenticationManager;

    private UserService userService;

    @Autowired
    public RegistrationController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public ResponseEntity<?> registerUserAccount(@Valid @RequestBody SignUpRequest signUpRequest){

        User existing = userService.findByEmail(signUpRequest.getEmail());
        if (existing != null){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        userService.save(signUpRequest);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
