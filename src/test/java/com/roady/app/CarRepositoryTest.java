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

    @Test
    public void testAddCar(){
        User user = userRepository.getReferenceById(2l);

        Car car = new Car();
        car.setPlateNumber("AAA123");
        car.setAvailableSeats(2);
        car.setCarType("AUDI");
        car.setUser(user);

        Car savedCar = carRepository.save(car);
        Car existsCar = entityManager.find(Car.class, savedCar.getId());

        user.setCar(existsCar);
        userRepository.save(user);
        System.out.println("Add new car: " + existsCar.getCarType());

        assertThat(existsCar.getPlateNumber().equals(car.getPlateNumber()));
    }
}

/*
@Rollback is a test annotation that is used to indicate whether a test-managed transaction
 should be rolled back after the test method has completed.
 */