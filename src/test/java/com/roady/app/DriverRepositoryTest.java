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
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.sql.Time;

import static org.assertj.core.api.Assertions.assertThat;
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
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
        Long requestId = 1l;
//        User driver = userRepository.getReferenceById(1l).getCar().getUser();
        Long driverId = rideRepository.getReferenceById(requestId).getCar().getUser().getId();

        DriverReview driverReview = new DriverReview();
        driverReview.setRideId(requestId);
        driverReview.setUserId(driverId);
        driverReview.setReview(8);

        DriverReview savedReview = driverReviewRepository.save(driverReview);
        DriverReview existsRide = entityManager.find(DriverReview.class, savedReview.getRideId());

        //then
        assertThat(existsRide.getRideId().equals(savedReview.getRideId()));

    }
}
