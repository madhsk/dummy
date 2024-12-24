package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.DTO.ActorDTO;
import com.springboot.filmrentalstore.exception.*;
import com.springboot.filmrentalstore.model.*;
import com.springboot.filmrentalstore.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/actors")
public class ActorController {
	@Autowired
	private ActorService actorService;

	@GetMapping("/lastname/{ln}")
	public ResponseEntity<List<ActorDTO>> getActorsByLastName(@PathVariable String ln)
			throws InvalidInputException, ResourceNotFoundException {
		List<ActorDTO> actors = actorService.getActorsByLastName(ln);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@GetMapping("/firstname/{fn}")
	public ResponseEntity<List<ActorDTO>> getActorsByFirstName(@PathVariable String fn)
			throws InvalidInputException, ResourceNotFoundException {
		List<ActorDTO> actors = actorService.getActorsByFirstName(fn);
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@PutMapping("/update/lastname/{id}")
	public ResponseEntity<Void> updateLastName(@PathVariable Long id, @RequestBody Map<String, String> requestBody)
			throws ResourceNotFoundException, InvalidInputException {
		String lastName = requestBody.get("lastName");
		actorService.updateLastName(id, lastName);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/update/firstname/{id}")
	public ResponseEntity<Void> updateFirstName(@PathVariable Long id, @RequestBody Map<String, String> requestBody)
			throws ResourceNotFoundException, InvalidInputException {
		String firstName = requestBody.get("firstName");
		actorService.updateFirstName(id, firstName);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/toptenbyfilmcount")
	public ResponseEntity<List<ActorDTO>> getTopTenActorsByFilmCount() throws ResourceNotFoundException {
		List<ActorDTO> actors = actorService.getTopTenActorsByFilmCount();
		return new ResponseEntity<>(actors, HttpStatus.OK);
	}

	@PostMapping("/post")
	public ResponseEntity<String> addActor(@RequestBody ActorDTO actorDTO) throws InvalidInputException {
		String response = actorService.addActor(actorDTO);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{actorId}/film")
	public ResponseEntity<String> assignFilmToActor(@PathVariable Long actorId, @RequestBody Collection<Long> filmIds)
			throws ResourceNotFoundException {
		try {
			actorService.assignFilmToActor(actorId, filmIds);
			return ResponseEntity.ok("Films assigned to Actor successfully");
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@GetMapping("/{id}/films")
	public ResponseEntity<List<Film>> getFilmsByActorId(@PathVariable Long id) throws ResourceNotFoundException {
		List<Film> films = actorService.getFilmsByActorId(id); // Adjust service method accordingly
		return ResponseEntity.ok(films);
	}
}
