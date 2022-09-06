package com.roady.app;

import com.roady.app.entities.PaymentType;
import com.roady.app.entities.Ride;
import com.roady.app.entities.User;
import com.roady.app.entities.Car;
import com.roady.app.repositories.CarRepository;
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

    @Test
    public void testAddRide() {
        User passenger = userRepository.getReferenceById(3l);
        Car car = carRepository.getReferenceById(2l);

        //given
        Ride ride = new Ride();
        //ride.setPassengers((List<User>) passenger);
        ride.setDeparturePoint("Vilnius");
        ride.setDestinationPoint("Klaipeda");
        ride.setDepartureDate(Date.valueOf("2022-09-20"));
        ride.setDepartureTime(Time.valueOf("10:00:00"));
        ride.setRidePrice(15D);
        ride.setPaymentType(PaymentType.CASH);
        ride.setAvailableSeats(3);
        ride.setCar(car);
        ride.setIsFinished(true);

        //when
        Ride savedRide = rideRepository.save(ride);
        Ride existsRide = entityManager.find(Ride.class, savedRide.getRideRequestId());

//        carRepository.save(car);
//        userRepository.save(user);

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

}
