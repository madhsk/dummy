package com.springboot.filmrentalstore.service;

import java.util.Collection;
import java.util.List;

import com.springboot.filmrentalstore.DTO.ActorDTO;
import com.springboot.filmrentalstore.exception.*;
import com.springboot.filmrentalstore.model.Film;

public interface IActorService {
	List<ActorDTO> getActorsByLastName(String ln) throws InvalidInputException, ResourceNotFoundException;

	List<ActorDTO> getActorsByFirstName(String fn) throws InvalidInputException, ResourceNotFoundException;

	void updateLastName(Long id, String lastName) throws ResourceNotFoundException, InvalidInputException;

	void updateFirstName(Long id, String firstName) throws ResourceNotFoundException, InvalidInputException;

	List<ActorDTO> getTopTenActorsByFilmCount() throws ResourceNotFoundException;

	String addActor(ActorDTO actorDTO) throws InvalidInputException;

	void assignFilmToActor(Long actorId, Collection<Long> filmIds) throws ResourceNotFoundException;

	List<Film> getFilmsByActorId(Long id) throws ResourceNotFoundException;

}
