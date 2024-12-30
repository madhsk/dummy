package com.springboot.filmrentalstore.service;
 
 
 
import com.springboot.filmrentalstore.DTO.StaffDTO;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.repo.StaffRepo;
import com.springboot.filmrentalstore.repo.StoreRepo;
import com.springboot.filmrentalstore.service.StaffService;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
 
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
@ExtendWith(MockitoExtension.class)
class StaffServiceTest {
 
    @InjectMocks
    private StaffService staffService;
 
    @Mock
    private StaffRepo staffRepository;
 
    @Mock
    private StoreRepo storeRepository;
 
    private ModelMapper modelMapper = new ModelMapper();
 
    private Staff staff;
    private StaffDTO staffDTO;
    private Store store;
    private Address address;
 
    @BeforeEach
    void setUp() {
        staff = new Staff();
        staff.setStaffId(1L);
        staff.setFirstName("John");
        staff.setLastName("Doe");
        staff.setEmail("john.doe@example.com");
 
        address = new Address();
        address.setPhone("1234567890");
        address.setCity(new City(1L,"coimbatore",new Country(1L,"India",LocalDateTime.now()),LocalDateTime.now()));
        staff.setAddress(address);
 
        store = new Store();
        store.setStoreId(1L);
 
        staffDTO = modelMapper.map(staff, StaffDTO.class);
    }
 
    @Test
    void addStaff() {
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);
 
        StaffDTO result = staffService.addStaff(staffDTO);
 
        assertNotNull(result);
        assertEquals(staff.getFirstName(), result.getFirstName());
        verify(staffRepository, times(1)).save(any(Staff.class));
    }
 
    @Test
    void findStaffByLastName() throws ResourceNotFoundException {
        when(staffRepository.findByLastName("Doe")).thenReturn(List.of(staff));
 
        List<StaffDTO> result = staffService.findStaffByLastName("Doe");
 
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(staff.getLastName(), result.get(0).getLastName());
        verify(staffRepository, times(1)).findByLastName("Doe");
    }
 
    @Test
    void findStaffByLastName_NotFound() {
        when(staffRepository.findByLastName("Nonexistent")).thenReturn(List.of());
 
        assertThrows(ResourceNotFoundException.class, () -> staffService.findStaffByLastName("Nonexistent"));
    }
    @Test
    void updateFirstName() throws ResourceNotFoundException {
        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);
 
        StaffDTO result = staffService.updateFirstName(1L, "Jane");
 
        assertNotNull(result);
        assertEquals("Jane", result.getFirstName());
        verify(staffRepository, times(1)).findById(1L);
        verify(staffRepository, times(1)).save(any(Staff.class));
    }
 
    @Test
    void updateFirstName_StaffNotFound() {
        when(staffRepository.findById(1L)).thenReturn(Optional.empty());
 
        assertThrows(ResourceNotFoundException.class, () -> staffService.updateFirstName(1L, "Jane"));
    }
 
    @Test
    void updateStore() throws ResourceNotFoundException {
        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        when(storeRepository.findById(1L)).thenReturn(Optional.of(store));
        when(staffRepository.save(any(Staff.class))).thenReturn(staff);
 
        StaffDTO result = staffService.updateStore(1L, store);
 
        assertNotNull(result);
        assertEquals(store.getStoreId(), staff.getStore().getStoreId());
        verify(staffRepository, times(1)).findById(1L);
        verify(storeRepository, times(1)).findById(1L);
        verify(staffRepository, times(1)).save(any(Staff.class));
    }
 
    @Test
    void updateStore_StoreNotFound() {
        when(staffRepository.findById(1L)).thenReturn(Optional.of(staff));
        when(storeRepository.findById(1L)).thenReturn(Optional.empty());
 
        assertThrows(ResourceNotFoundException.class, () -> staffService.updateStore(1L, store));
    }
 
    @Test
    void authenticateStaff_ValidCredentials() {
        when(staffRepository.findByUsername("username")).thenReturn(staff);
        staff.setPassword("password");
 
        StaffDTO result = staffService.authenticateStaff("username", "password");
 
        assertNotNull(result);
        verify(staffRepository, times(1)).findByUsername("username");
    }
 
    @Test
    void authenticateStaff_InvalidCredentials() {
        when(staffRepository.findByUsername("username")).thenReturn(staff);
        staff.setPassword("wrongPassword");
 
        StaffDTO result = staffService.authenticateStaff("username", "password");
 
        assertNull(result);
        verify(staffRepository, times(1)).findByUsername("username");
    }
}
 
 
 