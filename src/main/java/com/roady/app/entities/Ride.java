package com.roady.app.entities;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
@Data
@Table(name = "rideRequest")
public class Ride {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rideRequestId;

    @OneToMany (fetch = FetchType.LAZY)
    @JoinColumn(name = "passengerId")
    private List<User> passengers;
    @Column(nullable = false, length = 64)
    private String departurePoint;
    @Column(nullable = false, length = 64)
    private String destinationPoint;
    @Column(nullable = false)
    private Date departureDate;
    @Column(nullable = false)
    private Time departureTime;
    @Column(columnDefinition = "double default 0")
    private Double ridePrice;
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    @Column(nullable = false)
    private int availableSeats;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DriverId")
    private Car car;
    @ColumnDefault("true")
    private Boolean isFinished;

}
