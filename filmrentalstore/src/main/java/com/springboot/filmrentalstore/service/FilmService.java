package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.*;
import com.springboot.filmrentalstore.dao.*;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import jakarta.transaction.Transactional;
import com.springboot.filmrentalstore.model.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class FilmService implements IFilmService {

	@Autowired
	private FilmDAO filmRepository;

	@Autowired
	private ModelMapper modelMapper;


	@Autowired
	private FilmActorDAO filmActorRepository;


	@Autowired
	private ActorDAO actorRepository;

	@Autowired
	private CategoryDAO categoryRepository;

	@Autowired
	private FilmCategoryDAO filmCategoryRepository;



	@Override
	@Transactional
	public void addFilm(FilmDTO filmDTO) {
		Film film = modelMapper.map(filmDTO, Film.class);
		filmRepository.save(film);
		// TODO Auto-generated method stub
	}

	@Override
	public boolean deleteFilmById(int film_id) {
		// TODO Auto-generated method stub
		List<Film> film_list = filmRepository.findAll();

		for(Film film : film_list) {
			if(film.getFilmId()==film_id) {
				film_list.remove(film);
			}
		}

		return true;
	}


	@Override
	public FilmDTO findFilmsByTitle(String title) {
		Film film = filmRepository.findFilmByTitle(title);
		return film != null ? modelMapper.map(film, FilmDTO.class) : null;
	}

	@Override
	public List<FilmDTO> getAllFilm() {
		List<Film> films = filmRepository.findAll();

		films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList()).forEach(System.out::println);

		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}


	@Override
	public List<FilmDTO> findFilmsByReleaseYear(int releaseYear) {
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRelease_year() == releaseYear)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsByRentalDuration(int rd) {
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRental_duration() > rd)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsWhereRentalRateIsGreater(int rate) {
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRental_rate() > rate)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsWhereLengthIsGreater(int length) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getLength()>length)
				.collect(Collectors.toList());

		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsWhereRentalDurationIsLower(int rd) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRental_duration() < rd)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());

	}


	@Override
	public List<FilmDTO> findFilmsWhereRateIsLower(int rate) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRental_rate() < rate)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsWhereLengthIsLower(int length) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getLength()<length)
				.collect(Collectors.toList());

		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());

	}

	@Override
	public List<FilmDTO> findFilmBetweenYear(int from, int to) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRelease_year()>from && film.getRelease_year()<to)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsWhereRatingIsLower(int rating) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRating()<rating)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());
	}

	@Override
	public List<FilmDTO> findFilmsWhereRatingIsHigher(int rating) {
		// TODO Auto-generated method stub
		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getRating()>rating)
				.collect(Collectors.toList());
		return films.stream().map(film -> modelMapper.map(film, FilmDTO.class)).collect(Collectors.toList());


	}

	@Override
	public List<FilmDTO> findFilmsByLanguage(String lang) {
		// TODO Auto-generated method stub

		if (lang == null || lang.trim().isEmpty()) {
			throw new IllegalArgumentException("Language cannot be null or empty");
		}

		List<Film> films = filmRepository.findAll().stream()
				.filter(film -> film.getLanguage() != null && film.getLanguage().getName().equals(lang))
				.collect(Collectors.toList());

		return films.stream()
				.map(film -> modelMapper.map(film, FilmDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public Map<Integer, Integer> displayFilmsNumberByYear() {
		// TODO Auto-generated method stub
		Map<Integer,Integer> filmCountByYear= new HashMap<>();
		for(int i=1900; i<=2100;i++ ) {
			filmCountByYear.put(i, 0);
		}

		List<Film> films = filmRepository.findAll();

		for (Film film : films) {
			Integer releaseYear = film.getRelease_year();
			if (releaseYear != null && releaseYear >= 1900 && releaseYear <= 2100) {
				filmCountByYear.put(releaseYear, filmCountByYear.get(releaseYear) + 1);
			}
		}

		return filmCountByYear;
	}

	@Override
	public FilmDTO updateTitle(long id, String title) throws InvalidInputException {
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException("Film with ID " + id + " not found"));

		if (title == null || title.trim().isEmpty()) {
			throw new InvalidInputException("New title cannot be null or empty");
		}

		film.setTitle(title);

		Film updatedFilm = filmRepository.save(film);

		return modelMapper.map(updatedFilm, FilmDTO.class);
	}

	@Override
	public FilmDTO updateReleaseYear(long id,int year) throws InvalidInputException {
		// TODO Auto-generated method stub
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException("Film with ID " + id + " not found"));

		film.setRelease_year(year);

		Film updatedFilm = filmRepository.save(film);


		return modelMapper.map(updatedFilm, FilmDTO.class);
	}

	@Override
	public FilmDTO updateRentalDuration(long id, double rental_duration) throws InvalidInputException {
		// TODO Auto-generated method stub
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException("Film with ID " + id + " not found"));
		film.setRental_duration(rental_duration);
		Film updatedFilm = filmRepository.save(film);

		return modelMapper.map(updatedFilm, FilmDTO.class);
	}

	@Override
	public FilmDTO updateRentalRate(long id, double rental_rate) throws InvalidInputException {
		// TODO Auto-generated method stub
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException("Film with ID " + id + " not found"));
		film.setRental_rate(rental_rate);
		Film updatedFilm = filmRepository.save(film);

		return modelMapper.map(updatedFilm, FilmDTO.class);
	}

	@Override
	public FilmDTO updateRating(long id, int rating) throws InvalidInputException {
		// TODO Auto-generated method stub
		Optional<Film> film = Optional.of(filmRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException("Film with ID " + id + " not found")));

		film.get().setRating(rating);
		Film updatedFilm = filmRepository.save(film.get());
		return modelMapper.map(updatedFilm, FilmDTO.class);
	}

	@Override
	public FilmDTO updateLanguage(long id, int lang_id) throws InvalidInputException {
		// TODO Auto-generated method stub
		Film film = filmRepository.findById(id)
				.orElseThrow(() -> new InvalidInputException("Film with ID " + id + " not found")); 
		film.getLanguage().setLanguageId(lang_id);
		Film updatedFilm = filmRepository.save(film);
		return modelMapper.map(updatedFilm, FilmDTO.class);
	}





	@Transactional
	@Override
	public void assignActorsToFilm(Long filmId, Collection<Long> actorIds) {

		Film film = filmRepository.findById(filmId)
				.orElseThrow(() -> new IllegalArgumentException("Film with ID " + filmId + " not found"));

		for (Long actorId : actorIds) {
			Actor actor = actorRepository.findById(actorId)
					.orElseThrow(() -> new IllegalArgumentException("Actor with ID " + actorId + " not found"));

			// Check if the association already exists
			if (!filmActorRepository.existsByFilmAndActor(film, actor)) {
				FilmActor filmActor = new FilmActor();
				filmActor.setFilm(film);
				filmActor.setActor(actor);
				filmActor.setLastUpdate(LocalDateTime.now());

				filmActorRepository.save(filmActor);
			}
		}
	}

	@Override
	public FilmCategory updateCategory(long id, long category_id) throws InvalidInputException {
		// TODO Auto-generated method stub


		Optional<Film> film = filmRepository.findById(id);

		Optional<Category> category = categoryRepository.findById(category_id);

		FilmCategory filmCategory = new FilmCategory();
		if(!filmCategoryRepository.existsByFilmAndCategory(film,category)) {

			filmCategory.setFilm(film.get());
			filmCategory.setCategory(category.get());
			filmCategoryRepository.save(filmCategory);

		}


		return filmCategory;
	}

}