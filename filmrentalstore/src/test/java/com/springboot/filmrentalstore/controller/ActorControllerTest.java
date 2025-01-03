package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.ActorDTO;

import com.springboot.filmrentalstore.exception.InvalidInputException;

import com.springboot.filmrentalstore.exception.ResourceNotFoundException;

import com.springboot.filmrentalstore.model.Film;

import com.springboot.filmrentalstore.service.ActorService;

import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;

import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

class ActorControllerTest {

	@InjectMocks

	private ActorController actorController;

	@Mock

	private ActorService actorService;

	@BeforeEach

	void setUp() {

		MockitoAnnotations.openMocks(this);

	}

	@Test

	void testGetActorsByLastName() throws InvalidInputException, ResourceNotFoundException {

		String lastName = "Doe";

		List<ActorDTO> mockActors = Arrays.asList(new ActorDTO(1L, "John", "Doe"));

		when(actorService.getActorsByLastName(lastName)).thenReturn(mockActors);

		ResponseEntity<List<ActorDTO>> response = actorController.getActorsByLastName(lastName);

		assertEquals(200, response.getStatusCodeValue());

		assertEquals(mockActors, response.getBody());

		verify(actorService, times(1)).getActorsByLastName(lastName);

	}

	@Test

	void testGetActorsByFirstName() throws InvalidInputException, ResourceNotFoundException {

		String firstName = "John";

		List<ActorDTO> mockActors = Arrays.asList(new ActorDTO(1L, "John", "Doe"));

		when(actorService.getActorsByFirstName(firstName)).thenReturn(mockActors);

		ResponseEntity<List<ActorDTO>> response = actorController.getActorsByFirstName(firstName);

		assertEquals(200, response.getStatusCodeValue());

		assertEquals(mockActors, response.getBody());

		verify(actorService, times(1)).getActorsByFirstName(firstName);

	}

	@Test

	void testUpdateLastName() throws ResourceNotFoundException, InvalidInputException {

		Long actorId = 1L;

		String lastName = "Smith";

		Map<String, String> requestBody = new HashMap<>();

		requestBody.put("lastName", lastName);

		doNothing().when(actorService).updateLastName(actorId, lastName);

		ResponseEntity<Void> response = actorController.updateLastName(actorId, requestBody);

		assertEquals(200, response.getStatusCodeValue());

		verify(actorService, times(1)).updateLastName(actorId, lastName);

	}

	@Test

	void testAddActor() throws InvalidInputException {

		ActorDTO actorDTO = new ActorDTO(1L, "John", "Doe");

		String expectedResponse = "Actor added successfully";

		when(actorService.addActor(actorDTO)).thenReturn(expectedResponse);

		ResponseEntity<String> response = actorController.addActor(actorDTO);

		assertEquals(201, response.getStatusCodeValue());

		assertEquals(expectedResponse, response.getBody());

		verify(actorService, times(1)).addActor(actorDTO);

	}

	@Test

	void testAssignFilmToActor() throws ResourceNotFoundException {

		Long actorId = 1L;

		Collection<Long> filmIds = Arrays.asList(1L, 2L);

		doNothing().when(actorService).assignFilmToActor(actorId, filmIds);

		ResponseEntity<String> response = actorController.assignFilmToActor(actorId, filmIds);

		assertEquals(200, response.getStatusCodeValue());

		assertEquals("Films assigned to Actor successfully", response.getBody());

		verify(actorService, times(1)).assignFilmToActor(actorId, filmIds);

	}

	@Test

	void testGetFilmsByActorId() throws ResourceNotFoundException {

		Long actorId = 1L;

		List<Film> mockFilms = Arrays.asList(new Film(1L, "Film1"), new Film(2L, "Film2"));

		when(actorService.getFilmsByActorId(actorId)).thenReturn(mockFilms);

		ResponseEntity<List<Film>> response = actorController.getFilmsByActorId(actorId);

		assertEquals(200, response.getStatusCodeValue());

		assertEquals(mockFilms, response.getBody());

		verify(actorService, times(1)).getFilmsByActorId(actorId);

	}

	@Test

	void testGetTopTenActorsByFilmCount() throws ResourceNotFoundException {

		List<ActorDTO> mockActors = Arrays.asList(

				new ActorDTO(1L, "John", "Doe"),

				new ActorDTO(2L, "Jane", "Smith")

		);

		when(actorService.getTopTenActorsByFilmCount()).thenReturn(mockActors);

		ResponseEntity<List<ActorDTO>> response = actorController.getTopTenActorsByFilmCount();

		assertEquals(200, response.getStatusCodeValue());

		assertEquals(mockActors, response.getBody());

		verify(actorService, times(1)).getTopTenActorsByFilmCount();

	}

}
