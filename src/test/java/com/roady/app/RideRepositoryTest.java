package com.roady.app;

import com.roady.app.entities.*;
import com.roady.app.repositories.CarRepository;
import com.roady.app.repositories.DriverReviewRepository;
import com.roady.app.repositories.RideRepository;
import com.roady.app.repositories.UserRepository;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RideRepositoryTest {

    @Autowired
    private RideRepository rideRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DriverReviewRepository driverReviewRepository;

    @Test
    public void testAddRideRequest() {
        User passenger = userRepository.getReferenceById(4l);
        Car car = carRepository.getReferenceById(4l);

        //given
        Ride ride = new Ride();
        ride.setPassenger(passenger);
        ride.setDeparturePoint("Palanga");
        ride.setDestinationPoint("Riga");
        ride.setDepartureDate(Date.valueOf("2022-09-20"));
        ride.setDepartureTime(Time.valueOf("10:00:00"));
        ride.setRidePrice(5D);
        ride.setPaymentType(PaymentType.CASH);
        ride.setCar(car);
        ride.setIsFinished(true);

        //when
        Ride savedRide = rideRepository.save(ride);
        Ride existsRide = entityManager.find(Ride.class, savedRide.getRideRequestId());

        //then
        assertThat(existsRide.getRideRequestId().equals(ride.getRideRequestId()));

    }


    //WORKS
    @Test
    public void testFindRideRequestById(){
        Long rideRequestId = 2l;
        Optional<Ride> rideRequest = rideRepository.findById(rideRequestId);

        assertThat(rideRequest).isNotNull();
    }

    @Test
    public void addReviewToDriver(){
        Long requestId = 4l;
        Long driverId = rideRepository.getReferenceById(requestId).getCar().getUser().getId();
//        String driverName = rideRepository.getReferenceById(4l).getCar().getUser().getFirstName();

        DriverReview driverReview = new DriverReview();
        driverReview.setRideId(requestId);
        driverReview.setUserId(driverId);
        driverReview.setReview(4.5);

        DriverReview savedReview = driverReviewRepository.save(driverReview);
        DriverReview existsRide = entityManager.find(DriverReview.class, savedReview.getRideId());

        //then
        assertThat(existsRide.getRideId().equals(savedReview.getRideId()));

    }
}
