package com.metric.boet.api.service.auth;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.auth.LoginRequestAbstract;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.payloads.response.auth.JwtResponse;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.security.jwt.JwtUtils;
import com.metric.boet.api.security.services.UserDetailsImpl;
import com.metric.boet.api.service.auth.imp.SimpleAuthService;
import com.metric.boet.api.service.uiid.imp.SimpleUuidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class SimpleAuthServiceTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    SimpleUuidService simpleUuidService;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private SimpleAuthService simpleAuthService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateUserSuccess() {
        // given
        LoginRequestAbstract loginRequest = new LoginRequestAbstract();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        User user = new User();
        user.setId(1L);
        user.setUsername(loginRequest.getUsername());
        user.setPassword(loginRequest.getPassword());
        user.setEmail("test@test.com");

        UserDetailsImpl userDetails = new UserDetailsImpl(1L, loginRequest.getUsername(), user.getEmail(), passwordEncoder.encode(loginRequest.getPassword()), new ArrayList<>());

        List<String> roles = new ArrayList<>();

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                new UserDetailsImpl(1L, user.getUsername(), user.getEmail(), user.getPassword(), new ArrayList()),
                loginRequest.getPassword());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = "testjwt";
        JwtResponse expectedResponse = new JwtResponse(
                jwt,
                "Token Generated",
                user.mapToDTO(),
                true,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(user));
        when(jwtUtils.generateJwtToken(any())).thenReturn(jwt);

        // when
        BasicAPIResponse responseWrapper = simpleAuthService.authenticateUser(loginRequest);
        JwtResponse response = (JwtResponse) responseWrapper.getData();
        // then
        assertEquals(expectedResponse.getAccessToken(), response.getAccessToken());
        assertEquals(expectedResponse.getId(), response.getId());
        assertEquals(expectedResponse.getUsername(), response.getUsername());
        assertEquals(expectedResponse.getEmail(), response.getEmail());
        assertEquals(expectedResponse.getRoles(), response.getRoles());
    }

    @Test
    public void testAuthenticateUserFailure() {
        // given
        LoginRequestAbstract loginRequest = new LoginRequestAbstract();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException());

        // when
        BasicAPIResponse responseWrapper = simpleAuthService.authenticateUser(loginRequest);

        // then
        assertEquals(false, responseWrapper.getSuccessful());
    }
}
