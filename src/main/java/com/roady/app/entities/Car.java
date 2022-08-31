package com.roady.app.entities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "cars")
public class Car {
    @Id
    @Column(nullable = false, unique = true, length = 10)
    private String plateNumber;
    @Column(nullable = false, columnDefinition = "1")
    private int availableSeats;
    @OneToOne
    @JoinColumn(name = "user_id")
    @MapsId
    private User driverId;
}

//@MapsId annotation, which indicates that the primary key values will be copied from the User entity