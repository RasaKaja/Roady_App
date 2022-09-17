package com.roady.app.repositories;

import com.roady.app.entities.DriverReview;
import com.roady.app.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverReviewRepository extends JpaRepository<DriverReview, Long> {

    DriverReview findByRide(Ride ride);

//
//    public Iterable<DriverReview> findDriverReviewById(Long id);
//
//    //JPQL with index parameters
//    @Query(value = "SELECT AVG(review) FROM driver_review WHERE driver_id=?1", nativeQuery = true)
//    Double avgRating(Long userId);
//
//
//    //JPQL with named parameters
//    @Query(value = "SELECT AVG(review) FROM driver_review WHERE driver_id=:userId", nativeQuery = true)
//    Double driverAvgRating(@Param("driver_id") Long userId);
//
//
//    @Query(value = "SELECT AVG(review) FROM driver_review WHERE driver_id=?1", nativeQuery = true)
//    Double driverAvgRatings(Long userId);
}
