package com.metric.boet.api.service.beans;

import com.metric.boet.api.authorization.BasicUsers;
import com.metric.boet.api.entity.User;
import com.metric.boet.api.payloads.request.user.UserRequestAbstract;
import com.metric.boet.api.payloads.response.BasicAPIResponse;
import com.metric.boet.api.repository.UserRepository;
import com.metric.boet.api.service.mapper.imp.MapperService;
import com.metric.boet.api.service.uiid.imp.SimpleUuidService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private MapperService mapperService;

    @Mock
    private SimpleUuidService simpleUuidService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById() {
    }

    @Test
    void findByCreatedDate() {
    }

    @Test
    void findByUpdatedDate() {
    }

    @Test
    void findByCreatedUserId() {
    }

    @Test
    void findByCreatedUser() {
    }

    @Test
    void findByUpdatedUserId() {
    }

    @Test
    void findByUpdatedUser() {
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        // Create a mock SignupRequest
        UserRequestAbstract mockUserRequest = new UserRequestAbstract();
        mockUserRequest.setFirstName("test");
        mockUserRequest.setLastName("test");
        mockUserRequest.setUsername("testuser");
        mockUserRequest.setEmail("testuser@example.com");
        mockUserRequest.setPassword("123456");
        mockUserRequest.setPhone("+1234567890");

        String TEST_CODE = "TEST_CODE";

        User user = new User("TEST", mockUserRequest.getFirstName(), mockUserRequest.getLastName(), mockUserRequest.getPhone(), mockUserRequest.getUsername(), mockUserRequest.getEmail(), mockUserRequest.getPassword(), BasicUsers.ADMIN_USER);

        // Mock the UserRepository to return null when checking for existing user
        when(userRepository.existsByUsername(anyString())).thenReturn(false);
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(passwordEncoder.encode(any())).thenReturn(mockUserRequest.getPassword());
        when(simpleUuidService.getNextUserAccountCode()).thenReturn(TEST_CODE);

        // Verify that the response contains a success message
        BasicAPIResponse response = userService.create(mockUserRequest, BasicUsers.ADMIN_AUDIT);
        assertTrue(response.getSuccessful());
        assertEquals("User created successfully!", response.getMessage());

        // Verify that the UserRepository save() method was called with the correct User object
        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository, times(1)).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();
        assertEquals(mockUserRequest.getEmail(), savedUser.getEmail());
        assertEquals(TEST_CODE, savedUser.getAccountCode());
        assertEquals(mockUserRequest.getUsername(), savedUser.getUsername());
        assertEquals(mockUserRequest.getPassword(), savedUser.getPassword());
        assertEquals(mockUserRequest.getPhone(), savedUser.getPhone());
    }

    @Test
    public void testCreateUserFailureDuplicateUsername() throws Exception {
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
        BasicAPIResponse basicAPIResponse = userService.create(request, BasicUsers.ADMIN_AUDIT);
        assertEquals(basicAPIResponse.getMessage(), "Error: Username is already taken!");
        assertFalse(basicAPIResponse.getSuccessful());
    }

    @Test
    public void testCreateUserFailureDuplicateEmail() throws Exception {
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
        BasicAPIResponse basicAPIResponse = userService.create(request, BasicUsers.ADMIN_AUDIT);
        assertEquals(basicAPIResponse.getMessage(), "Error: Email is already in use!");
        assertFalse(basicAPIResponse.getSuccessful());
    }

    @Test
    void replace() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}