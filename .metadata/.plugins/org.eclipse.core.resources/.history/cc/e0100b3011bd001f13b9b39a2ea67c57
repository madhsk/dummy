package com.springboot.filmrentalstore.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "staff")
@Data
@NoArgsConstructor
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    @Column(nullable = false, length = 45)
    private String firstName;

    @Column(nullable = false, length = 45)
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "address_id", nullable = false)
    private Address address;

    @Lob
    private byte[] picture;

    private String email;

    @ManyToOne
    @JoinColumn(name = "store_id", nullable = false)
    private Store store; // Correct relationship

    private int active;
    private String username;
    private String password;

    @Column(name = "last_update", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime lastUpdate;
}
