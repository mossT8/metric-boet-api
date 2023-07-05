package com.metric.boet.api.service.auth.imp;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.payloads.request.auth.LoginRequest;
import com.metric.boet.api.payloads.request.user.UserRequest;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.payloads.response.auth.JwtResponse;
import com.metric.boet.api.security.jwt.JwtUtils;
import com.metric.boet.api.security.services.UserDetailsImpl;
import com.metric.boet.api.service.auth.IAuthService;
import com.metric.boet.api.service.databeans.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
public class SimpleAuthService implements IAuthService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

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
    public BasicAPIResponse registerUser(UserRequest signUpRequest) {
        return userService.create(signUpRequest, BasicUsers.ADMIN_AUDIT);
    }
}
