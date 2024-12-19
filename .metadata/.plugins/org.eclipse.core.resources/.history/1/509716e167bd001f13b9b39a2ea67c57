package com.springboot.filmrentalstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "film_category")
@Data
@NoArgsConstructor
public class FilmCategory {
	@EmbeddedId
	private FilmCategoryId filmcategoryId;
	
    @Column(name = "last_update", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime lastUpdate;
}


@Embeddable
class FilmCategoryId {
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "film_id", referencedColumnName = "film_id", nullable = false)
	private Film film;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "category_id", referencedColumnName = "category_id", nullable = false)
	private Category category;

}