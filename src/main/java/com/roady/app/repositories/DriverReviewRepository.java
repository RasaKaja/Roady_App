package com.roady.app.repositories;

import com.roady.app.entities.DriverReview;
import com.roady.app.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DriverReviewRepository extends JpaRepository<DriverReview, Long> {

    DriverReview findByRide(Ride ride);

}
