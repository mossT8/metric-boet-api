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
public class SimpleAuthServiceTests {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

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

    @Test
    public void testRegisterUserSuccess() throws Exception {
        // Create a mock SignupRequest
        UserRequestAbstract mockUserRequest = new UserRequestAbstract();
        mockUserRequest.setFirstName("test");
        mockUserRequest.setLastName("test");
        mockUserRequest.setUsername("testuser");
        mockUserRequest.setEmail("testuser@example.com");
        mockUserRequest.setPassword("123456");
        mockUserRequest.setPhone("+1234567890");

        User user = new User("TEST", mockUserRequest.getFirstName(), mockUserRequest.getLastName(), mockUserRequest.getPhone(), mockUserRequest.getUsername(), mockUserRequest.getEmail(), mockUserRequest.getPassword(), BasicUsers.ADMIN_USER);

        // Mock the UserRepository to return null when checking for existing user
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn(mockUserRequest.getPassword());

        // Verify that the response contains a success message
        BasicAPIResponse response = simpleAuthService.registerUser(mockUserRequest);
        assertTrue(response.getSuccessful());
        assertEquals("User registered successfully!", response.getMessage());

        // Verify that the UserRepository save() method was called with the correct User object
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals(mockUserRequest.getEmail(), savedUser.getEmail());
        assertEquals(mockUserRequest.getUsername(), savedUser.getUsername());
        assertEquals(mockUserRequest.getPassword(), savedUser.getPassword());
        assertEquals(mockUserRequest.getPhone(), savedUser.getPhone());
    }

    @Test
    public void testRegisterUserFailureDuplicateUsername() throws Exception {
        // given
        UserRequestAbstract request = new UserRequestAbstract();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("johndoe");
        request.setEmail("johndoe@example.com");
        request.setPassword("password");
        request.setPhone("1234567890");

        given(userRepository.existsByUsername(request.getUsername())).willReturn(true);

        // assert
        BasicAPIResponse basicAPIResponse = simpleAuthService.registerUser(request);
        assertEquals(basicAPIResponse.getMessage(), "Error: Username is already taken!");
        assertFalse(basicAPIResponse.getSuccessful());
    }

    @Test
    public void testRegisterUserFailureDuplicateEmail() throws Exception {
        // given
        UserRequestAbstract request = new UserRequestAbstract();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setUsername("johndoe");
        request.setEmail("johndoe@example.com");
        request.setPassword("password");
        request.setPhone("1234567890");

        given(userRepository.existsByEmail(request.getEmail())).willReturn(true);

        // assert
        BasicAPIResponse basicAPIResponse = simpleAuthService.registerUser(request);
        assertEquals(basicAPIResponse.getMessage(), "Error: Email is already in use!");
        assertFalse(basicAPIResponse.getSuccessful());
    }
}
