package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Film;
import com.springboot.filmrentalstore.model.Language;
import com.springboot.filmrentalstore.model.Actor;
import com.springboot.filmrentalstore.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    // Add a new Film
    @PostMapping("/post")
    public String addFilm(@RequestBody Film film) {
        return filmService.addFilm(film);
    }

    // Search Films by Title
    @GetMapping("/title/{title}")
    public List<Film> searchFilmsByTitle(@PathVariable String title) {
        return filmService.searchFilmsByTitle(title);
    }

    // Search Films by Release Year
    @GetMapping("/year/{year}")
    public List<Film> searchFilmsByReleaseYear(@PathVariable int year) {
        return filmService.searchFilmsByReleaseYear(year);
    }

    // Search Films with Rental Duration greater than {rd}
    @GetMapping("/duration/gt/{rd}")
    public List<Film> searchFilmsByRentalDurationGreaterThan(@PathVariable int rd) {
        return filmService.searchFilmsByRentalDurationGreaterThan(rd);
    }

    // Search Films with Rental Rate greater than {rate}
    @GetMapping("/rate/gt/{rate}")
    public List<Film> searchFilmsByRentalRateGreaterThan(@PathVariable double rate) {
        return filmService.searchFilmsByRentalRateGreaterThan(rate);
    }

    // Search Films with Length greater than {length}
    @GetMapping("/length/gt/{length}")
    public List<Film> searchFilmsByLengthGreaterThan(@PathVariable int length) {
        return filmService.searchFilmsByLengthGreaterThan(length);
    }

    // Search Films with Rental Duration less than {rd}
    @GetMapping("/duration/lt/{rd}")
    public List<Film> searchFilmsByRentalDurationLessThan(@PathVariable int rd) {
        return filmService.searchFilmsByRentalDurationLessThan(rd);
    }

    // Search Films with Rental Rate less than {rate}
    @GetMapping("/rate/lt/{rate}")
    public List<Film> searchFilmsByRentalRateLessThan(@PathVariable double rate) {
        return filmService.searchFilmsByRentalRateLessThan(rate);
    }

    // Search Films with Length less than {length}
    @GetMapping("/length/lt/{length}")
    public List<Film> searchFilmsByLengthLessThan(@PathVariable int length) {
        return filmService.searchFilmsByLengthLessThan(length);
    }

    // Search Films between two years
    @GetMapping("/betweenyear/{from}/{to}")
    public List<Film> searchFilmsBetweenYears(@PathVariable int from, @PathVariable int to) {
        return filmService.searchFilmsBetweenYears(from, to);
    }

    // Search Films with Rating less than {rating}
    @GetMapping("/rating/lt/{rating}")
    public List<Film> searchFilmsByRatingLessThan(@PathVariable double rating) {
        return filmService.searchFilmsByRatingLessThan(rating);
    }

    // Search Films with Rating greater than {rating}
    @GetMapping("/rating/gt/{rating}")
    public List<Film> searchFilmsByRatingGreaterThan(@PathVariable double rating) {
        return filmService.searchFilmsByRatingGreaterThan(rating);
    }

    // Search Films by Language
    @GetMapping("/language/{lang}")
    public List<Film> searchFilmsByLanguage(@PathVariable String lang) {
        return filmService.searchFilmsByLanguage(lang);
    }

    // Count Films by Year
    @GetMapping("/countbyyear")
    public List<Object[]> countFilmsByYear() {
        return filmService.countFilmsByYear();
    }

    // Get Actors of a Film by Film ID
    @GetMapping("/{id}/actors")
    public List<Actor> getActorsByFilmId(@PathVariable int id) {
        return filmService.findActorsByFilmId(id);
    }

    // Get Films by Category
    @GetMapping("/category/{category}")
    public List<Film> getFilmsByCategory(@PathVariable String category) {
        return filmService.findFilmsByCategory(category);
    }

    // Assign Actor to Film
    @PutMapping("/{id}/actor")
    public String assignActorToFilm(@PathVariable int id, @RequestBody Actor actor) {
        return filmService.assignActorToFilm(id, actor);
    }

    // Update Film Title
    @PutMapping("/update/title/{id}")
    public Film updateFilmTitle(@PathVariable int id, @RequestBody String title) {
        return filmService.updateFilmTitle(id, title);
    }

    // Update Film Release Year
    @PutMapping("/update/releaseyear/{id}")
    public Film updateFilmReleaseYear(@PathVariable int id, @RequestBody int year) {
        return filmService.updateFilmReleaseYear(id, year);
    }

    // Update Film Rental Duration
    @PutMapping("/update/rentaldurtion/{id}")
    public Film updateFilmRentalDuration(@PathVariable int id, @RequestBody int duration) {
        return filmService.updateFilmRentalDuration(id, duration);
    }

    // Update Film Rental Rate
    @PutMapping("/update/rentalrate/{id}")
    public Film updateFilmRentalRate(@PathVariable int id, @RequestBody BigDecimal rate) {
        return filmService.updateFilmRentalRate(id, rate);
    }

    // Update Film Rating
    @PutMapping("/update/rating/{id}")
    public Film updateFilmRating(@PathVariable int id, @RequestBody String rating) {
        return filmService.updateFilmRating(id, rating);
    }

    // Update Film Language
    @PutMapping("/update/language/{id}")
    public Film updateFilmLanguage(@PathVariable int id, @RequestBody Language language) {
        return filmService.updateFilmLanguage(id, language);
    }

}
