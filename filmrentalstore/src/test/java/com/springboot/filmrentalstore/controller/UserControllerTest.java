package com.springboot.filmrentalstore.controller;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
 
import com.springboot.filmrentalstore.model.Role;
import com.springboot.filmrentalstore.model.UserEntity;
import com.springboot.filmrentalstore.service.*;
 
import java.util.*;
 
public class UserControllerTest {
 
    @Mock
    private UserService userService;  // Mocking the UserService
 
    @Mock
    private RoleService roleService;  // Mocking the RoleService
 
    @Mock
    private PasswordEncoder passwordEncoder;  // Mocking the PasswordEncoder
 
    @InjectMocks
    private UserController userController;  // Injecting mocks into the controller
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }
 
    // Test for registering a user
    @Test
    void testRegisterUser_Success() {
        // Arrange
        UserEntity user = new UserEntity("testUser", "testPassword", new ArrayList<>());
        
        // Mock password encoding
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        
//        // Mock saveUser method (void method) to do nothing (or simulate success)
//        doNothing().when(userService).saveUser(any(UserEntity.class));  // Use doNothing() for void methods
        
        // Act
        ResponseEntity<?> response = userController.registerUser(user);
        
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());  // Check if status is CREATED (201)
        assertNotNull(response.getBody());  // Ensure the response body is not null
        // You can add more assertions here to check the response body, like success message or code
    }
 
 
    // Test for failing to register a user
    @Test
    void testRegisterUser_Failure() {
        // Arrange
        UserEntity user = new UserEntity("testUser", "testPassword", new ArrayList<>());
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        doThrow(new RuntimeException("DB error")).when(userService).saveUser(any(UserEntity.class));  // Simulate an exception for saveUser (void method)
 
        // Act
        ResponseEntity<?> response = userController.registerUser(user);
 
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());  // Check if status is INTERNAL_SERVER_ERROR (500)
        assertNotNull(response.getBody());  // Ensure the response body is not null
//        assertTrue(((Response) response.getBody()).getCode().equals("REGISTERFAIL"));  // Check the failure code
//        assertTrue(((Response) response.getBody()).getMessage().equals("Error creating user"));  // Check the failure message
    }
 
    // Test for updating a user by adding the ROLE_MANAGER role
    @Test
    void testUpdateUser_AddRoleManager_Success() throws Exception {
        // Arrange
        Long userId = 1L;
        UserEntity existingUser = new UserEntity("existingUser", "password", new ArrayList<>());
        when(userService.getUserById(userId)).thenReturn(existingUser);  // Mock getting the existing user
        Role managerRole = new Role("ROLE_MANAGER");
 
//        // Mock the save methods
//        doNothing().when(roleService).saveRole(any(Role.class));  // Mock roleService.saveRole (void method)
//        doNothing().when(userService).saveUser(any(UserEntity.class));  // Mock userService.saveUser (void method)
 
        // Act
        ResponseEntity<?> response = userController.updateUser(userId);
 
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());  // Ensure the status is CREATED (201)
        assertNotNull(response.getBody());  // Ensure the response body is not null
//        assertTrue(((Response) response.getBody()).getCode().equals("REGISTERSUCCESS"));  // Check success code
//        assertTrue(((Response) response.getBody()).getMessage().equals("User updated successfully with selected roles"));  // Check success message
 
        // Verify that saveRole and saveUser were called
        verify(roleService, times(1)).saveRole(any(Role.class));  // Ensure roleService.saveRole was called once
        verify(userService, times(1)).saveUser(existingUser);  // Ensure userService.saveUser was called once
    }
 
    // Test for updating a user when they already have the ROLE_MANAGER role
    @Test
    void testUpdateUser_HasRoleManager_Success() throws Exception {
        // Arrange
        Long userId = 1L;
        Role existingRole = new Role("ROLE_MANAGER");
        List<Role> roles = new ArrayList<>();
        roles.add(existingRole);
        UserEntity existingUser = new UserEntity("existingUser", "password", roles);
 
        when(userService.getUserById(userId)).thenReturn(existingUser);  // Mock getting the existing user
 
//        // Mock the save methods
//        doNothing().when(roleService).saveRole(any(Role.class));  // Mock roleService.saveRole (void method)
//        doNothing().when(userService).saveUser(any(UserEntity.class));  // Mock userService.saveUser (void method)
 
        // Act
        ResponseEntity<?> response = userController.updateUser(userId);
 
        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());  // Ensure the status is CREATED (201)
        assertNotNull(response.getBody());  // Ensure the response body is not null
//        assertTrue(((Response) response.getBody()).getCode().equals("REGISTERSUCCESS"));  // Check success code
//        assertTrue(((Response) response.getBody()).getMessage().equals("User updated successfully with selected roles"));  // Check success message
 
        // Verify that saveRole and saveUser were not called
//        verify(roleService, times(0)).saveRole(any(Role.class));  // Ensure saveRole was not called
//        verify(userService, times(1)).saveUser(existingUser);  // Ensure saveUser was called once
    }
 
    // Test for failing to update a user
    @Test
    void testUpdateUser_Failure() throws Exception {
        // Arrange
        Long userId = 1L;
        when(userService.getUserById(userId)).thenThrow(new RuntimeException("User not found"));  // Simulate user not found
 
        // Act
        ResponseEntity<?> response = userController.updateUser(userId);
 
        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());  // Ensure status is INTERNAL_SERVER_ERROR (500)
        assertNotNull(response.getBody());  // Ensure the response body is not null
//        assertTrue(((Response) response.getBody()).getCode().equals("REGISTERFAIL"));  // Check failure code
//        assertTrue(((Response) response.getBody()).getMessage().equals("Error creating user"));  // Check failure message
    }
}
 
 