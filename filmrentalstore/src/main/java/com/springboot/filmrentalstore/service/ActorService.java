package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.ActorDTO;
import com.springboot.filmrentalstore.dao.*;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.exception.ResourceNotFoundException;
import com.springboot.filmrentalstore.model.Actor;
import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.FilmActor;
import com.springboot.filmrentalstore.model.FilmActorId;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class ActorService implements IActorService {
	@Autowired
	private ActorDAO actorDao;



	@Autowired
	private FilmActorDAO filmActorDAO;

	@Autowired
	private FilmDAO filmDAO;	

	public List<ActorDTO> getActorsByLastName(String lastName) throws InvalidInputException, ResourceNotFoundException {
		if (lastName == null) {
			throw new InvalidInputException("Last name cannot be null or empty");
		}
		List<Actor> actors = actorDao.findByLastName(lastName);
		if (actors.isEmpty()) {
			throw new ResourceNotFoundException("No actors found with last name: " + lastName);
		}
		List<ActorDTO> actordto=new ArrayList<>();
		for(Actor a: actors) {
			actordto.add(convertToDTO(a));
		}
		return actordto;
	}

	public List<ActorDTO> getActorsByFirstName(String firstName) throws InvalidInputException, ResourceNotFoundException {
		if (firstName == null) {
			throw new InvalidInputException("First name cannot be null or empty");
		}
		List<Actor> actors = actorDao.findByFirstName(firstName);
		if (actors.isEmpty()) {
			throw new ResourceNotFoundException("No actors found with first name: " + firstName);
		}
		List<ActorDTO> actordto=new ArrayList<>();
		for(Actor a: actors) {
			actordto.add(convertToDTO(a));
		}
		return actordto;
	}

	public void updateLastName(Long id, String lastName) throws ResourceNotFoundException, InvalidInputException {
		if (lastName == null || lastName.isEmpty()) {
			throw new InvalidInputException("Last name cannot be null or empty");
		}
		Actor actor = actorDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
		actor.setLastName(lastName);
		actorDao.save(actor);
	}

	public void updateFirstName(Long id, String firstName) throws ResourceNotFoundException, InvalidInputException {
		if (firstName == null || firstName.isEmpty()) {
			throw new InvalidInputException("First name cannot be null or empty");
		}
		Actor actor = actorDao.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Actor not found with id: " + id));
		actor.setFirstName(firstName);
		actorDao.save(actor);
	}

	public List<ActorDTO> getTopTenActorsByFilmCount() throws ResourceNotFoundException {
		List<Actor> actors = actorDao.findAll();
		if (actors.isEmpty()) {
			throw new ResourceNotFoundException("No actors found");
		}
		List<ActorDTO> actordto = new ArrayList<>();
		int count = 0;
		for (Actor a : actors) {
		    if (count == 10) {
		        break; 
		    }
		    actordto.add(convertToDTO(a));
		    count++;
		}
		return actordto;

	}
	
	@Override
	public String addActor(ActorDTO actorDTO) throws InvalidInputException {
		if (actorDTO.getFirstName() == null || actorDTO.getFirstName().isEmpty() ||
				actorDTO.getLastName() == null || actorDTO.getLastName().isEmpty()) {
			throw new InvalidInputException("First name and Last name cannot be null or empty");
		}
		Actor actor = convertToEntity(actorDTO);
		actorDao.save(actor);
		return "Record Created Successfully";
	}


	private ActorDTO convertToDTO(Actor actor) {
		return new ActorDTO(actor.getActorId(), actor.getFirstName(), actor.getLastName());
	}

	private Actor convertToEntity(ActorDTO actorDTO) {
		Actor actor = new Actor();
		actor.setActorId(actorDTO.getActorId());
		actor.setFirstName(actorDTO.getFirstName());
		actor.setLastName(actorDTO.getLastName());
		return actor;
	}

	@Override
	public void assignFilmToActor(Long actorId, Collection<Long> filmIds) throws ResourceNotFoundException {
		// TODO Auto-generated method stub

		Actor actor = actorDao.findById(actorId)
				.orElseThrow(() -> new ResourceNotFoundException("Actor with ID " + actorId + " not found"));


		for(long filmId : filmIds) {
			Film film = filmDAO.findById(filmId)
					.orElseThrow(()-> new ResourceNotFoundException("Film with Id "+filmId+" not found")) ;

			if (!filmActorDAO.existsByFilmAndActor(film, actor)) {
				FilmActor filmActor = new FilmActor();
				filmActor.setFilm(film);
				filmActor.setActor(actor);
				filmActor.setLastUpdate(LocalDateTime.now());

				filmActorDAO.save(filmActor);
			}
		}
	}

	@Override
	public List<Film> getFilmsByActorId(Long id) throws ResourceNotFoundException {
		List<FilmActor> filmActor_list = filmActorDAO.findAll();

		List<Film> film_list = new ArrayList<>();

		for(FilmActor filmActor : filmActor_list) {
			if(filmActor.getActor().getActorId()==id) {
				film_list.add(filmActor.getFilm());

			}
		}
		
		if(film_list.isEmpty()) {
			throw new ResourceNotFoundException("No films found for the given actor");
		}
		return film_list;
	}

	
}
