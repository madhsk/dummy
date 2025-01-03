package com.springboot.filmrentalstore.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
 
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
 
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.exception.*;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.repo.*;

 
class FilmServiceTest {
 
    @InjectMocks
    private FilmService filmService;
 
    @Mock
    private FilmRepo filmRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
 
    @Test
    void testAddFilm() {
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle("Test Film");
 
        Film film = new Film();
        film.setTitle("Test Film");
 
        when(modelMapper.map(filmDTO, Film.class)).thenReturn(film);
 
        filmService.addFilm(filmDTO);
 
        verify(filmRepository, times(1)).save(film);
    }
 
    @Test
    void testFindFilmsByTitle() {
        String title = "Test Film";
        Film film = new Film();
        film.setTitle(title);
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle(title);
 
        when(filmRepository.findFilmByTitle(title)).thenReturn(film);
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(filmDTO);
 
        FilmDTO result = filmService.findFilmsByTitle(title);
 
        assertNotNull(result);
        assertEquals(title, result.getTitle());
    }
 
    @Test
    void testDeleteFilmById() {
        Film film = new Film();
        film.setFilmId(1L);
        List<Film> films = Collections.singletonList(film);
 
        when(filmRepository.findAll()).thenReturn(films);
 
        boolean result = filmService.deleteFilmById(1);
 
        assertTrue(result);
        verify(filmRepository, times(1)).findAll();
    }
 
    @Test
    void testGetAllFilm() {
        Film film1 = new Film();
        film1.setTitle("Film 1");
 
        Film film2 = new Film();
        film2.setTitle("Film 2");
 
        List<Film> films = Arrays.asList(film1, film2);
 
        FilmDTO filmDTO1 = new FilmDTO();
        filmDTO1.setTitle("Film 1");
 
        FilmDTO filmDTO2 = new FilmDTO();
        filmDTO2.setTitle("Film 2");
 
        when(filmRepository.findAll()).thenReturn(films);
        when(modelMapper.map(film1, FilmDTO.class)).thenReturn(filmDTO1);
        when(modelMapper.map(film2, FilmDTO.class)).thenReturn(filmDTO2);
 
        List<FilmDTO> result = filmService.getAllFilm();
 
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Film 1", result.get(0).getTitle());
        assertEquals("Film 2", result.get(1).getTitle());
    }
 
    @Test
    void testUpdateTitle() throws InvalidInputException {
        long filmId = 1;
        String newTitle = "Updated Title";
 
        Film film = new Film();
        film.setFilmId(filmId);
 
        when(filmRepository.findById(filmId)).thenReturn(Optional.of(film));
        when(filmRepository.save(any(Film.class))).thenReturn(film);
 
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setTitle(newTitle);
 
        when(modelMapper.map(film, FilmDTO.class)).thenReturn(filmDTO);
 
        FilmDTO result = filmService.updateTitle(filmId, newTitle);
 
        assertNotNull(result);
        assertEquals(newTitle, result.getTitle());
    }
 
    @Test
    void testFindFilmsByReleaseYear() {
        int releaseYear = 2020;
 
        Film film1 = new Film();
        film1.setRelease_year(2020);
 
        Film film2 = new Film();
        film2.setRelease_year(2020);
 
        List<Film> films = Arrays.asList(film1, film2);
 
        FilmDTO filmDTO1 = new FilmDTO();
        FilmDTO filmDTO2 = new FilmDTO();
 
        when(filmRepository.findAll()).thenReturn(films);
        when(modelMapper.map(film1, FilmDTO.class)).thenReturn(filmDTO1);
        when(modelMapper.map(film2, FilmDTO.class)).thenReturn(filmDTO2);
 
        List<FilmDTO> result = filmService.findFilmsByReleaseYear(releaseYear);
 
        assertNotNull(result);
        assertEquals(2, result.size());
    }
}