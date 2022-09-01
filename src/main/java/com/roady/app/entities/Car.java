package com.roady.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 10, unique = true)
    private String plateNumber;
    @Column(nullable = false)
    private String carType;
    @Column(columnDefinition = "integer default 1", nullable = false)
    private int availableSeats;
    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "car")
    private User user;
}

//@MapsId annotation, which indicates that the primary key values will be copied from the User entity