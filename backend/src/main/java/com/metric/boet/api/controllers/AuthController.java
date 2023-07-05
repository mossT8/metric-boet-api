package com.metric.boet.api.controllers;

import javax.validation.Valid;

import com.metric.boet.api.payloads.request.auth.LoginRequest;
import com.metric.boet.api.payloads.request.user.UserRequest;

import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.payloads.response.auth.JwtResponse;
import com.metric.boet.api.service.auth.imp.SimpleAuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    SimpleAuthService simpleAuthService;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        JwtResponse response = simpleAuthService.authenticateUser(loginRequest);

        if (response.getSuccessful()) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new BasicAPIResponse("Details provided don`t seem to work on our side! Please contact System support should this reoccur.", false));
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserRequest signUpRequest) {
        return ResponseEntity.ok(simpleAuthService.registerUser(signUpRequest));
    }
}
