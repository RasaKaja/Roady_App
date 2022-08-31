package com.roady.app;

import com.roady.app.entities.Car;
import com.roady.app.entities.User;
import com.roady.app.repositories.CarRepository;
import com.roady.app.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;
//    @Autowired
//    private User user;

    @Test
    public void testAddCar(){
        Car car = new Car();
        car.setPlateNumber("ABC123");
        car.setAvailableSeats(2);
        User driverId = userRepository.getReferenceById(4l);
        car.setDriverId(driverId);

        Car savedCar = carRepository.save(car);
        Car existsCar = entityManager.find(Car.class, savedCar.getDriverId());

        assertThat(existsCar.getPlateNumber().equals(car.getPlateNumber()));

    }
}

/*
@Rollback is a test annotation that is used to indicate whether a test-managed transaction
 should be rolled back after the test method has completed.
 */
