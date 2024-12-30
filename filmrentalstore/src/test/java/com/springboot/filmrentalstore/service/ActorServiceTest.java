package com.springboot.filmrentalstore.service;
 
import com.springboot.filmrentalstore.DTO.ActorDTO;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Actor;
import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.FilmActor;
import com.springboot.filmrentalstore.repo.ActorRepo;
import com.springboot.filmrentalstore.repo.FilmActorRepo;
import com.springboot.filmrentalstore.repo.FilmRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
 
import java.time.LocalDateTime;
import java.util.*;
 
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
class ActorServiceTest {
 
    @InjectMocks
    private ActorService actorService;
 
    @Mock
    private ActorRepo actorRepo;
 
    @Mock
    private FilmActorRepo filmActorRepo;
 
    @Mock
    private FilmRepo filmRepo;
 
    private Actor sampleActor;
 
    private ActorDTO sampleActorDTO;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleActor = new Actor(1L, "John", "Doe", LocalDateTime.now());
        sampleActorDTO = new ActorDTO(1L, "John", "Doe", LocalDateTime.now());
    }
 
    @Test
    void testGetActorsByLastName_Success() throws ResourceNotFoundException, InvalidInputException {
        // Arrange
        String lastName = "Doe";
        List<Actor> actors = List.of(sampleActor);
        when(actorRepo.findByLastName(lastName)).thenReturn(actors);
 
        // Act
        List<ActorDTO> result = actorService.getActorsByLastName(lastName);
 
        // Assert
        assertEquals(1, result.size());
        assertEquals(sampleActor.getLastName(), result.get(0).getLastName());
        verify(actorRepo, times(1)).findByLastName(lastName);
    }
 
    @Test
    void testGetActorsByLastName_InvalidInput() {
        // Arrange
        String lastName = null;
 
        // Act & Assert
        assertThrows(InvalidInputException.class, () -> actorService.getActorsByLastName(lastName));
    }
 
    @Test
    void testGetActorsByLastName_NotFound() {
        // Arrange
        String lastName = "Unknown";
        when(actorRepo.findByLastName(lastName)).thenReturn(Collections.emptyList());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> actorService.getActorsByLastName(lastName));
    }
 
    @Test
    void testGetActorsByFirstName_Success() throws ResourceNotFoundException, InvalidInputException {
        // Arrange
        String firstName = "John";
        List<Actor> actors = List.of(sampleActor);
        when(actorRepo.findByFirstName(firstName)).thenReturn(actors);
 
        // Act
        List<ActorDTO> result = actorService.getActorsByFirstName(firstName);
 
        // Assert
        assertEquals(1, result.size());
        assertEquals(sampleActor.getFirstName(), result.get(0).getFirstName());
        verify(actorRepo, times(1)).findByFirstName(firstName);
    }
 
    @Test
    void testUpdateLastName_Success() throws ResourceNotFoundException, InvalidInputException {
        // Arrange
        Long actorId = 1L;
        String newLastName = "Smith";
        when(actorRepo.findById(actorId)).thenReturn(Optional.of(sampleActor));
 
        // Act
        actorService.updateLastName(actorId, newLastName);
 
        // Assert
        assertEquals(newLastName, sampleActor.getLastName());
        verify(actorRepo, times(1)).save(sampleActor);
    }
 
    @Test
    void testUpdateLastName_InvalidInput() {
        // Arrange
        Long actorId = 1L;
        String newLastName = "";
 
        // Act & Assert
        assertThrows(InvalidInputException.class, () -> actorService.updateLastName(actorId, newLastName));
    }
 
    @Test
    void testUpdateLastName_ActorNotFound() {
        // Arrange
        Long actorId = 1L;
        String newLastName = "Smith";
        when(actorRepo.findById(actorId)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> actorService.updateLastName(actorId, newLastName));
    }
 
    @Test
    void testAddActor_Success() throws InvalidInputException {
        // Arrange
        when(actorRepo.save(any(Actor.class))).thenReturn(sampleActor);
 
        // Act
        String result = actorService.addActor(sampleActorDTO);
 
        // Assert
        assertEquals("Record Created Successfully", result);
        verify(actorRepo, times(1)).save(any(Actor.class));
    }
 
    @Test
    void testAddActor_InvalidInput() {
        // Arrange
        ActorDTO invalidActorDTO = new ActorDTO(null, "", null, null);
 
        // Act & Assert
        assertThrows(InvalidInputException.class, () -> actorService.addActor(invalidActorDTO));
    }
 
    @Test
    void testAssignFilmToActor_Success() throws ResourceNotFoundException {
        // Arrange
        Long actorId = 1L;
        Long filmId = 2L;
        Film film = new Film();
        film.setFilmId(filmId);
        when(actorRepo.findById(actorId)).thenReturn(Optional.of(sampleActor));
        when(filmRepo.findById(filmId)).thenReturn(Optional.of(film));
        when(filmActorRepo.existsByFilmAndActor(film, sampleActor)).thenReturn(false);
 
        // Act
        actorService.assignFilmToActor(actorId, List.of(filmId));
 
        // Assert
        verify(filmActorRepo, times(1)).save(any(FilmActor.class));
    }
 
    @Test
    void testAssignFilmToActor_ActorNotFound() {
        // Arrange
        Long actorId = 1L;
        Long filmId = 2L;
        when(actorRepo.findById(actorId)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> actorService.assignFilmToActor(actorId, List.of(filmId)));
    }
 
    @Test
    void testGetFilmsByActorId_Success() throws ResourceNotFoundException {
        // Arrange
        Long actorId = 1L;
        Film film = new Film();
        film.setFilmId(2L);
        FilmActor filmActor = new FilmActor();
        filmActor.setFilm(film);
        filmActor.setActor(sampleActor);
        when(filmActorRepo.findAll()).thenReturn(List.of(filmActor));
 
        // Act
        List<Film> films = actorService.getFilmsByActorId(actorId);
 
        // Assert
        assertEquals(1, films.size());
        assertEquals(film.getFilmId(), films.get(0).getFilmId());
    }
 
    @Test
    void testGetFilmsByActorId_NotFound() {
        // Arrange
        Long actorId = 1L;
        when(filmActorRepo.findAll()).thenReturn(Collections.emptyList());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> actorService.getFilmsByActorId(actorId));
    }
}
 
 