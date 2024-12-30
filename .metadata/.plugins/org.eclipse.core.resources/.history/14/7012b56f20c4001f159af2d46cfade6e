package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.ActorDTO;
import com.springboot.filmrentalstore.controller.ActorController;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.service.ActorService;
 
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class ActorControllerTest {
 
    @Mock
    private ActorService actorService;
 
    @InjectMocks
    private ActorController actorController;
 
    public ActorControllerTest() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testGetActorsByLastName() throws InvalidInputException, ResourceNotFoundException {
        String lastName = "Doe";
        List<ActorDTO> mockActors = Arrays.asList(new ActorDTO(1L, "John", "Doe"));
        when(actorService.getActorsByLastName(lastName)).thenReturn(mockActors);
 
        ResponseEntity<List<ActorDTO>> response = actorController.getActorsByLastName(lastName);
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        assertEquals("Doe", response.getBody().get(0).getLastName());
        verify(actorService, times(1)).getActorsByLastName(lastName);
    }
 
    @Test
    void testAddActor() throws InvalidInputException {
        ActorDTO actorDTO = new ActorDTO(1L, "John", "Doe");
        when(actorService.addActor(actorDTO)).thenReturn("Actor added successfully");
 
        ResponseEntity<String> response = actorController.addActor(actorDTO);
 
        assertEquals(201, response.getStatusCodeValue());
        assertEquals("Actor added successfully", response.getBody());
        verify(actorService, times(1)).addActor(actorDTO);
    }
 
    @Test
    void testUpdateFirstName() throws InvalidInputException, ResourceNotFoundException {
        Long actorId = 1L;
        String newFirstName = "Jane";
        doNothing().when(actorService).updateFirstName(actorId, newFirstName);
 
        ResponseEntity<Void> response = actorController.updateFirstName(actorId, Collections.singletonMap("firstName", newFirstName));
 
        assertEquals(200, response.getStatusCodeValue());
        verify(actorService, times(1)).updateFirstName(actorId, newFirstName);
    }
 
    @Test
    void testGetTopTenActorsByFilmCount() throws ResourceNotFoundException {
        List<ActorDTO> mockActors = Arrays.asList(new ActorDTO(1L, "John", "Doe"));
        when(actorService.getTopTenActorsByFilmCount()).thenReturn(mockActors);
 
        ResponseEntity<List<ActorDTO>> response = actorController.getTopTenActorsByFilmCount();
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().size());
        verify(actorService, times(1)).getTopTenActorsByFilmCount();
    }
 
    @Test
    void testGetFilmsByActorId() throws ResourceNotFoundException {
        Long actorId = 1L;
        List<Film> mockFilms = Arrays.asList(new Film(1L, "Film A"), new Film(2L, "Film B"));
        when(actorService.getFilmsByActorId(actorId)).thenReturn(mockFilms);
 
        ResponseEntity<List<Film>> response = actorController.getFilmsByActorId(actorId);
 
        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Film A", response.getBody().get(0).getTitle());
        verify(actorService, times(1)).getFilmsByActorId(actorId);
    }
}
 
 
 