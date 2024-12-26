package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.FilmDTO;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.FilmCategory;
import com.springboot.filmrentalstore.service.FilmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FilmControllerTest {

    @InjectMocks
    private FilmController filmController;

    @Mock
    private FilmService filmService;

    @Test
    void addFilm_ShouldReturnSuccessMessage() throws InvalidInputException {
        FilmDTO filmDTO = new FilmDTO();
        doNothing().when(filmService).addFilm(filmDTO);

        ResponseEntity<String> response = filmController.addFilm(filmDTO);

        assertEquals("Record Saved", response.getBody());
        verify(filmService, times(1)).addFilm(filmDTO);
    }

    @Test
    void deleteFilmById_ShouldReturnSuccessMessage() throws ResourceNotFoundException {
        int filmId = 1;
        when(filmService.deleteFilmById(filmId)).thenReturn(true);

        ResponseEntity<String> response = filmController.deleteFilmById(filmId);

        assertEquals("Film record deleted", response.getBody());
        verify(filmService, times(1)).deleteFilmById(filmId);
    }

    @Test
    void deleteFilmById_ShouldThrowResourceNotFoundException() {
        int filmId = 1;
        when(filmService.deleteFilmById(filmId)).thenReturn(false);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            filmController.deleteFilmById(filmId);
        });

        assertEquals("Film record not found.", exception.getMessage());
        verify(filmService, times(1)).deleteFilmById(filmId);
    }

    @Test
    void getAllFilm_ShouldReturnListOfFilms() {
        List<FilmDTO> films = Arrays.asList(new FilmDTO(), new FilmDTO());
        when(filmService.getAllFilm()).thenReturn(films);

        List<FilmDTO> response = filmController.getAllFilm();

        assertEquals(2, response.size());
        verify(filmService, times(1)).getAllFilm();
    }

    @Test
    void findByTitle_ShouldReturnFilm() throws ResourceNotFoundException {
        String title = "Test Film";
        FilmDTO filmDTO = new FilmDTO();
        when(filmService.findFilmsByTitle(title)).thenReturn(filmDTO);

        ResponseEntity<FilmDTO> response = filmController.findByTitle(title);

        assertNotNull(response.getBody());
        verify(filmService, times(1)).findFilmsByTitle(title);
    }

    @Test
    void findByTitle_ShouldThrowResourceNotFoundException() {
        String title = "Test Film";
        when(filmService.findFilmsByTitle(title)).thenReturn(null);

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            filmController.findByTitle(title);
        });

        assertEquals("Film with title Test Film not found", exception.getMessage());
        verify(filmService, times(1)).findFilmsByTitle(title);
    }

    @Test
    void findFilmsByReleaseYear_ShouldReturnFilms() throws ResourceNotFoundException {
        int year = 2020;
        List<FilmDTO> films = Arrays.asList(new FilmDTO(), new FilmDTO());
        when(filmService.findFilmsByReleaseYear(year)).thenReturn(films);

        ResponseEntity<List<FilmDTO>> response = filmController.findFilmsByReleaseYear(year);

        assertEquals(2, response.getBody().size());
        verify(filmService, times(1)).findFilmsByReleaseYear(year);
    }

    @Test
    void findFilmsByReleaseYear_ShouldThrowResourceNotFoundException() {
        int year = 2020;
        when(filmService.findFilmsByReleaseYear(year)).thenReturn(Collections.emptyList());

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            filmController.findFilmsByReleaseYear(year);
        });

        assertEquals("No records of films found for 2020 release year", exception.getMessage());
        verify(filmService, times(1)).findFilmsByReleaseYear(year);
    }

    @Test
    void updateTitle_ShouldReturnUpdatedFilm() throws InvalidInputException {
        int filmId = 1;
        String newTitle = "Updated Title";
        FilmDTO updatedFilm = new FilmDTO();
        when(filmService.updateTitle(filmId, newTitle)).thenReturn(updatedFilm);

        ResponseEntity<FilmDTO> response = filmController.updateTitle(filmId, newTitle);

        assertNotNull(response.getBody());
        verify(filmService, times(1)).updateTitle(filmId, newTitle);
    }

    @Test
    void assignActorsToFilm_ShouldReturnSuccessMessage() throws InvalidInputException {
        Long filmId = 1L;
        Collection<Long> actorIds = Arrays.asList(1L, 2L);
        doNothing().when(filmService).assignActorsToFilm(filmId, actorIds);

        ResponseEntity<String> response = filmController.assignActorsToFilm(filmId, actorIds);

        assertEquals("Actors assigned to film successfully", response.getBody());
        verify(filmService, times(1)).assignActorsToFilm(filmId, actorIds);
    }

    @Test
    void updateCategory_ShouldReturnUpdatedFilmCategory() throws InvalidInputException {
        long filmId = 1L;
        long categoryId = 2L;
        FilmCategory updatedCategory = new FilmCategory();
        when(filmService.updateCategory(filmId, categoryId)).thenReturn(updatedCategory);

        ResponseEntity<FilmCategory> response = filmController.updateCategory(filmId, categoryId);

        assertNotNull(response.getBody());
        verify(filmService, times(1)).updateCategory(filmId, categoryId);
    }
}
