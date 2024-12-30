package com.springboot.filmrentalstore.service;
 
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import com.springboot.filmrentalstore.model.UserEntity;
import com.springboot.filmrentalstore.repo.UserRepo;
 
import java.util.Optional;
 
public class UserServiceTest {
 
    @Mock
    private UserRepo userRepo;  // Mock dependency
 
    private UserService userService;
 
    // Set up the mock and the service before each test
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize the mocks
        userService = new UserService();  // Create instance of service
        userService.userRepo = userRepo;  // Inject the mock UserRepo into the service
    }
 
    // Test case 1: Save a user successfully
    @Test
    void testSaveUser() {
        // Arrange: Create a user and mock repository behavior
        UserEntity user = new UserEntity("john_doe", "password123", null);
        when(userRepo.save(user)).thenReturn(user);
 
        // Act: Call the service method
        UserEntity savedUser = userService.saveUser(user);
 
        // Assert: Verify that the saved user matches the input user
        assertEquals(user.getUsername(), savedUser.getUsername());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }
 
    // Test case 2: Check if a user exists by ID (user exists)
    @Test
    void testFindById_whenUserExists() {
        // Arrange: Mock repository to return a user
        UserEntity user = new UserEntity("john_doe", "password123", null);
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
 
        // Act: Call the service method
        boolean exists = userService.findById(1L);
 
        // Assert: Verify that the user exists
        assertTrue(exists);
    }
 
    // Test case 3: Check if a user exists by ID (user does not exist)
    @Test
    void testFindById_whenUserDoesNotExist() {
        // Arrange: Mock repository to return empty for user
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
 
        // Act: Call the service method
        boolean exists = userService.findById(1L);
 
        // Assert: Verify that the user does not exist
        assertFalse(exists);
    }
 
    // Test case 4: Delete a user by ID
    @Test
    void testDeleteUser() {
        // Arrange: Create a user and mock repository behavior
        UserEntity user = new UserEntity("john_doe", "password123", null);
        doNothing().when(userRepo).deleteById(1L);
 
        // Act: Call the service method
        userService.deleteUser(1L);
 
        // Assert: Verify that deleteById was called
        verify(userRepo, times(1)).deleteById(1L);
    }
 
    // Test case 5: Get user by ID (user exists)
    @Test
    void testGetUserById_whenUserExists() {
        // Arrange: Create a user and mock repository behavior
        UserEntity user = new UserEntity("john_doe", "password123", null);
        when(userRepo.findById(1L)).thenReturn(Optional.of(user));
 
        // Act: Call the service method
        UserEntity foundUser = userService.getUserById(1L);
 
        // Assert: Verify that the returned user matches the one in the mock
        assertEquals(user.getUsername(), foundUser.getUsername());
        assertEquals(user.getPassword(), foundUser.getPassword());
    }
 
    // Test case 6: Get user by ID (user does not exist)
    @Test
    void testGetUserById_whenUserDoesNotExist() {
        // Arrange: Mock repository to return empty for user
        when(userRepo.findById(1L)).thenReturn(Optional.empty());
 
        // Act & Assert: Call the service method and expect an exception
        assertThrows(java.util.NoSuchElementException.class, () -> {
            userService.getUserById(1L);
        });
    }
}
 
 