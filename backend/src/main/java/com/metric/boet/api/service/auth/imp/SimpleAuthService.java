package com.metric.boet.api.service.auth.imp;

import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.auth.LoginRequest;
import com.metric.boet.api.payloads.request.auth.RegisterRequest;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.payloads.response.auth.JwtResponse;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.security.jwt.JwtUtils;
import com.metric.boet.api.security.services.UserDetailsImpl;

import com.metric.boet.api.service.auth.IAuthService;
import com.metric.boet.api.service.uiid.imp.SimpleUuidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SimpleAuthService implements IAuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    SimpleUuidService simpleUuidService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtUtils jwtUtils;

    @Override
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

          return new JwtResponse(jwt,
                    "Generated Token",
                    true,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles);
        } catch (Exception e) {
            return new JwtResponse("Error", "Something unexpected happened on our side when trying to validate username and password", false, 0L, "Error", "Error", new ArrayList<>());
        }
    }


    @Override
    public BasicAPIResponse registerUser(RegisterRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return new BasicAPIResponse("Error: Username is already taken!", false);
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new BasicAPIResponse("Error: Email is already in use!", false);
        }

        User user = new User();

        user.setFirstName(signUpRequest.getFirstName());
        user.setAccountCode(simpleUuidService.getNextUserAccountCode());
        user.setLastName(signUpRequest.getLastName());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setEmail(signUpRequest.getEmail());
        user.setPhone(signUpRequest.getPhone());

        userRepository.save(user);

        return new BasicAPIResponse("User registered successfully!", true);
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void setJwtUtils(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }
}
