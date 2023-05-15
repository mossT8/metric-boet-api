package com.metric.boet.api.auth;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.metric.boet.api.core.authorization.BasicUsers;
import com.metric.boet.api.core.authorization.IUserAudit;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payload.request.LoginRequest;
import com.metric.boet.api.payload.request.SignupRequest;
import com.metric.boet.api.payload.response.BasicMessageResponse;
import com.metric.boet.api.payload.response.JwtResponse;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.security.jwt.JwtUtils;
import com.metric.boet.api.security.services.UserDetailsImpl;
import com.metric.boet.api.service.auth.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AuthServiceTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtUtils jwtUtils;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAuthenticateUserSuccess() {
        // given
        LoginRequest loginRequest = new LoginRequest();
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
        JwtResponse expectedResponse = new JwtResponse(jwt, userDetails.getId(),
                userDetails.getUsername(), userDetails.getEmail(), roles);

        when(authenticationManager.authenticate(any())).thenReturn(authentication);
        when(userRepository.findByUsername(loginRequest.getUsername())).thenReturn(Optional.of(user));
        when(jwtUtils.generateJwtToken(any())).thenReturn(jwt);

        // when
        ResponseEntity<?> response = authService.authenticateUser(loginRequest);

        // then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody() instanceof JwtResponse);
        JwtResponse actualResponse = (JwtResponse) response.getBody();
        assertEquals(expectedResponse.getAccessToken(), actualResponse.getAccessToken());
        assertEquals(expectedResponse.getId(), actualResponse.getId());
        assertEquals(expectedResponse.getUsername(), actualResponse.getUsername());
        assertEquals(expectedResponse.getEmail(), actualResponse.getEmail());
        assertEquals(expectedResponse.getRoles(), actualResponse.getRoles());
    }

    @Test
    public void testAuthenticateUserFailure() {
        // given
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("testuser");
        loginRequest.setPassword("testpassword");

        when(authenticationManager.authenticate(any())).thenThrow(new RuntimeException());

        // when
        ResponseEntity<?> response = authService.authenticateUser(loginRequest);

        // then
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testRegisterUserSuccess() throws Exception {
        // Create a mock SignupRequest
        SignupRequest mockSignupRequest = new SignupRequest();
        mockSignupRequest.setFirstName("test");
        mockSignupRequest.setLastName("test");
        mockSignupRequest.setUsername("testuser");
        mockSignupRequest.setEmail("testuser@example.com");
        mockSignupRequest.setPassword("123456");
        mockSignupRequest.setPhone("+1234567890");

        User user = new User("TEST", mockSignupRequest.getFirstName(), mockSignupRequest.getLastName(), mockSignupRequest.getPhone(), mockSignupRequest.getUsername(), mockSignupRequest.getEmail(), mockSignupRequest.getPassword(), BasicUsers.ADMIN_USER);

        // Mock the UserRepository to return null when checking for existing user
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn(mockSignupRequest.getPassword());

        // Verify that the response contains a success message
        BasicMessageResponse response = authService.registerUser(mockSignupRequest);
        assertTrue(response.getSuccessful());
        assertEquals("User registered successfully!", response.getMessage());

        // Verify that the UserRepository save() method was called with the correct User object
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals(mockSignupRequest.getEmail(), savedUser.getEmail());
        assertEquals(mockSignupRequest.getUsername(), savedUser.getUsername());
        assertEquals(mockSignupRequest.getPassword(), savedUser.getPassword());
        assertEquals(mockSignupRequest.getPhone(), savedUser.getPhone());
    }

    @Test
    public void testRegisterUserFailureDuplicateUsername() throws Exception {
        // given
        SignupRequest request = new SignupRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("johndoe");
        request.setEmail("johndoe@example.com");
        request.setPassword("password");
        request.setPhone("1234567890");

        given(userRepository.existsByUsername(request.getUsername())).willReturn(true);

        // assert
        BasicMessageResponse basicMessageResponse = authService.registerUser(request);
        assertEquals(basicMessageResponse.getMessage(), "Error: Username is already taken!");
        assertFalse(basicMessageResponse.getSuccessful());
    }

    @Test
    public void testRegisterUserFailureDuplicateEmail() throws Exception {
        // given
        SignupRequest request = new SignupRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("johndoe");
        request.setEmail("johndoe@example.com");
        request.setPassword("password");
        request.setPhone("1234567890");

        given(userRepository.existsByEmail(request.getEmail())).willReturn(true);

        // assert
        BasicMessageResponse basicMessageResponse = authService.registerUser(request);
        assertEquals(basicMessageResponse.getMessage(), "Error: Email is already in use!");
        assertFalse(basicMessageResponse.getSuccessful());
    }
}
