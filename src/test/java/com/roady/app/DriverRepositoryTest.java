package com.roady.app;

import com.roady.app.entities.DriverReview;
import com.roady.app.entities.PaymentType;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.repositories.DriverReviewRepository;
import com.roady.app.repositories.RideRepository;
import com.roady.app.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Date;
import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;

public class DriverRepositoryTest {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DriverReviewRepository driverReviewRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void addReviewToDriver(){
//        User driver = userRepository.getReferenceById(1l).getCar().getUser();
        Long driverId = rideRepository.getReferenceById(4l).getCar().getUser().getId();

        DriverReview driverReview = new DriverReview();
        driverReview.setRideId(6l);
        driverReview.setUserId(driverId);
        driverReview.setReview(4.5);

        DriverReview savedReview = driverReviewRepository.save(driverReview);
        DriverReview existsRide = entityManager.find(DriverReview.class, savedReview.getRideId());

        //then
        assertThat(existsRide.getRideId().equals(savedReview.getRideId()));

    }
}
