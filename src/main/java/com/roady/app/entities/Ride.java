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
    @OneToOne (cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "passengerId")
    private User passenger;
    @Column(nullable = false, length = 64)
    private String departurePoint;
    @Column(nullable = false, length = 64)
    private String destinationPoint;
    @Column(nullable = false)
    private String departureDate;
    @Column(nullable = false)
    private String departureTime;
    @Column(length = 20)
    private String ridePrice;
    @Column (length = 20)
//    @Enumerated(EnumType.STRING)
    private String paymentType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "CarId")
    private Car car;
    @ColumnDefault("false")
    private Boolean isFinished;

}
