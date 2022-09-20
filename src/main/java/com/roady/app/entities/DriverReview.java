package com.roady.app.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "driver_review")
public class DriverReview{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "driver_id")
    private Long userId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "RideId")
    private Ride ride;

    @Column(name = "review")
    private double review;

//    @Override
//    public double getReview() {
//        return review;
//    }
//
//    @Override
//    public Long getId() {
//        return id;
//    }
//
//    @Override
//    public void setId(Long id) {
//        this.id=id;
//    }
//
//    @Override
//    public Long getUserId() {
//        return userId;
//    }
//
//    @Override
//    public void setUserId(Long userId) {
//        this.userId = userId;
//    }
//
//    @Override
//    public void setReview(double review) {
//        this.review = review;
//    }
//
//    public Long getRideId() {
//        return rideId;
//    }
//
//    public void setRideId(Long rideId) {
//        this.rideId = rideId;
//    }
}