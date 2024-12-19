package com.springboot.filmrentalstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "film_category")
@Data
@NoArgsConstructor
@IdClass(FilmCategoryId.class)
public class FilmCategory {
    @Id
    @ManyToOne
    @JoinColumn(name = "film_id", nullable = false)
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "last_update", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime lastUpdate;
}

// Composite key class
@Data
@NoArgsConstructor
class FilmCategoryId implements java.io.Serializable {
    private int film;
    private int category;
}
