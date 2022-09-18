package com.roady.app.entities;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;

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
    private Date departureDate;
    @Column(nullable = false)
    private String departureTime;
    @Column(columnDefinition = "double default 0")
    private String ridePrice;
    @Column
   // @Enumerated(EnumType.STRING)
    //private PaymentType paymentType;
    private String paymentType;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DriverId")
    private Car car;
    @Column
//    @Enumerated(EnumType.STRING)
//    private RideStatus rideStatus;
    @ColumnDefault("false")
    private Boolean isFinished;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "DriverReviewId")
    private DriverReview driverReview;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "PassengerReviewId")
    private PassengerReview passengerReview;

}
