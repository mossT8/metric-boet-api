package com.metric.boet.api.service.auth.imp;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.auth.LoginRequestAbstract;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.payloads.response.auth.JwtResponse;
import com.metric.boet.api.security.jwt.JwtUtils;
import com.metric.boet.api.security.services.UserDetailsImpl;
import com.metric.boet.api.service.auth.IAuthService;
import com.metric.boet.api.service.beans.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public BasicAPIResponse authenticateUser(LoginRequestAbstract loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            String jwt = jwtUtils.generateJwtToken(userDetails.getUsername());
            User user = userService.findBeanByUsername(userDetails.getUsername());

            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            JwtResponse jwtResponse = new JwtResponse(jwt,
                    "Generated Token",
                    user.mapToDTO(),
                    true,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    roles);

            BasicAPIResponse basicAPIResponse = new BasicAPIResponse();
            basicAPIResponse.setData(jwtResponse);

            return basicAPIResponse;
        } catch (Exception e) {
            return new BasicAPIResponse("Error: Something unexpected happened on our side when trying to validate username and password", false);
        }
    }


    @Override
    public BasicAPIResponse registerUser(UserRequestAbstract signUpRequest) {
        return userService.create(signUpRequest, BasicUsers.SYSTEM_AUTO_AUDIT);
    }
}
